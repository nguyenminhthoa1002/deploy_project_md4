package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ra.jwt.JwtTokenProvider;
import ra.model.entity.ERole;
import ra.model.entity.Orders;
import ra.model.entity.Roles;
import ra.model.entity.Users;
import ra.model.service.IOrderService;
import ra.model.service.IRoleService;
import ra.model.service.IUserService;
import ra.payload.request.ChangePassword;
import ra.payload.request.LoginRequest;
import ra.payload.request.RegisterRequest;
import ra.payload.respone.JwtResponse;
//import ra.payload.response.JwtResponse;
import ra.payload.respone.MessageResponse;
import ra.security.CustomUserDetailService;
import ra.security.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private IOrderService orderService;

    //    ------------------    ĐĂNG KÝ   ----------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signupRequest) throws Throwable {
        if (userService.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users user = new Users();
        user.setUserName(signupRequest.getUserName());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setAvatar(signupRequest.getAvatar());
        user.setLastName(signupRequest.getLastName());
        user.setFirstName(signupRequest.getFirstName());
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());
        user.setAddress(signupRequest.getAddress());
        user.setUserStatus(true);
        LocalDateTime time = LocalDateTime.now();
        user.setUserCreateDate(time);
        Set<String> strRoles = signupRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (strRoles == null) {
            //User quyen mac dinh
            Roles userRole = (Roles) roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = null;
                        try {
                            adminRole = (Roles) roleService.findByRoleName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(adminRole);
                    case "moderator":
                        Roles modRole = null;
                        try {
                            modRole = (Roles) roleService.findByRoleName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(modRole);
                    case "user":
                        Roles userRole = null;
                        try {
                            userRole = (Roles) roleService.findByRoleName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(userRole);
                }
            });
        }
        user.setListRoles(listRoles);
        Users userUpdate = (Users) userService.saveOrUpdate(user);
        Orders orders = new Orders();
        orders.setUsers(userUpdate);
        orderService.saveOrUpdate(orders);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }


    //    ------------------------- ĐĂNG NHẬP ---------------------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
        if (!customUserDetail.isUserStatus()){
            return ResponseEntity.badRequest().body(new MessageResponse("User is locked"));
        } else {
            //Sinh JWT tra ve client
            String jwt = tokenProvider.generateToken(customUserDetail);
            //Lay cac quyen cua user
            List<String> listRoles = customUserDetail.getAuthorities().stream()
                    .map(item -> item.getAuthority()).collect(Collectors.toList());
            Orders orders = customUserDetail.getListOrder().get(customUserDetail.getListOrder().size()-1);
            return ResponseEntity.ok(new JwtResponse(jwt, customUserDetail.getUsername(), customUserDetail.getAvatar(), customUserDetail.getLastName(), customUserDetail.getFirstName(),
                    customUserDetail.getEmail(), customUserDetail.getPhone(), customUserDetail.getAddress(), orders,listRoles));
        }
    }

    //    ------------------------- USER ĐANG ĐĂNG NHẬP -------------------------
    @GetMapping("/loggingInUser")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> loggingInUser() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    //    -------------------   ROLE: ADMIN   -------------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUser() {
        return userService.showAllUser();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Users getUserById(@PathVariable("userId") int userId) {
        return (Users) userService.findById(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest signupRequest) {
        try {
            return registerUser(signupRequest);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("userId") int userId, @RequestBody RegisterRequest registerRequest) throws Throwable {
        Users userUpdate = (Users) userService.findById(userId);
        userUpdate.setUserStatus(registerRequest.isUserStatus());
        LocalDateTime time = LocalDateTime.now();
        userUpdate.setUserCreateDate(time);
        Set<String> strRoles = registerRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (strRoles == null) {
            //User quyen mac dinh
            Roles userRole = (Roles) roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = null;
                        try {
                            adminRole = (Roles) roleService.findByRoleName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(adminRole);
                    case "moderator":
                        Roles modRole = null;
                        try {
                            modRole = (Roles) roleService.findByRoleName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(modRole);
                    case "user":
                        Roles userRole = null;
                        try {
                            userRole = (Roles) roleService.findByRoleName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                        listRoles.add(userRole);
                }
            });
        }
        userUpdate.setListRoles(listRoles);
        userService.saveOrUpdate(userUpdate);
        return ResponseEntity.ok(new MessageResponse("Update successfully!"));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok().body("Delete success");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Users> searchUserByName(@RequestParam("searchName") String searchName) {
        return userService.searchUser(searchName);
    }

    //    --------------------- ROLE : MODERATOR ----------------------------
    @GetMapping("getAllUserForModerator")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Users> getAllUserForModerator() {
        List<Users> usersForModerator = new ArrayList<>();
        List<Users> listUser = userService.findAll();
        Set<Roles> roleUser = new HashSet<>();
        Roles userRole = new Roles(3,ERole.ROLE_USER);
        roleUser.add(userRole);
        for (Users user : listUser) {
            if (user.getListRoles().containsAll(roleUser)&&user.getListRoles().size()==1){
                usersForModerator.add(user);
            }

        }
        return usersForModerator;
    }

    @PostMapping("createNewUser")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> createUserforModerator(@RequestBody RegisterRequest signupRequest){
        if (userService.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users user = new Users();
        user.setUserName(signupRequest.getUserName());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setLastName(signupRequest.getLastName());
        user.setFirstName(signupRequest.getFirstName());
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());
        user.setAddress(signupRequest.getAddress());
        user.setUserStatus(true);
        LocalDateTime time = LocalDateTime.now();
        user.setUserCreateDate(time);
        Set<Roles> roleUser = new HashSet<>();
        Roles userRole = new Roles(3,ERole.ROLE_USER);
        roleUser.add(userRole);
        user.setListRoles(roleUser);
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PutMapping("updateUserForModerator/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateUserForModerator(@PathVariable("userId") int userId, @RequestBody RegisterRequest registerRequest){
        Users userUpdateModerator = (Users) userService.findById(userId);
        userUpdateModerator.setUserStatus(registerRequest.isUserStatus());
        LocalDateTime time = LocalDateTime.now();
        userUpdateModerator.setUserCreateDate(time);
        userService.saveOrUpdate(userUpdateModerator);
        return ResponseEntity.ok(new MessageResponse("Update successfully!"));
    }

//    ----------------------- ROLE : USER ------------------------
    @PutMapping("updateUserForUser/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserForUser(@PathVariable("userId") int userId, @RequestBody RegisterRequest registerRequest){
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users userUpdateUser = (Users) userService.findById(userId);
        userUpdateUser.setFirstName(registerRequest.getFirstName());
        userUpdateUser.setLastName(registerRequest.getLastName());
        userUpdateUser.setPhone(registerRequest.getPhone());
        userUpdateUser.setAddress(registerRequest.getAddress());
        userUpdateUser.setEmail(registerRequest.getEmail());
        LocalDateTime time = LocalDateTime.now();
        userUpdateUser.setUserCreateDate(time);
        userService.saveOrUpdate(userUpdateUser);
        return ResponseEntity.ok(new MessageResponse("Update successfully!"));
    }

    @PostMapping("changePassword")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword){
//        USER dang dang nhap
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findUsersByUserName(usersChangePass.getUsername());
//        thong tin request
        String userName = changePassword.getUserName();
        String oldPass = changePassword.getOldPass();
        String newPass = changePassword.getNewPass();

        if (usersChangePass.getUsername().equals(userName)&& BCrypt.checkpw(oldPass,usersChangePass.getPassword())){
            users.setPassword(encoder.encode(newPass));
            userService.saveOrUpdate(users);
        }
        return ResponseEntity.ok(new MessageResponse("Change password successfully!"));
    }

    @PostMapping("logout")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new MessageResponse("Logout successfully!"));
    }


}
