package ra.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.model.entity.Orders;
import ra.model.entity.Users;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private int userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String avatar;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime userCreateDate;
    private boolean userStatus;
    private List<Orders> listOrder;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public static CustomUserDetails mapUserToUserDetail(Users users){
        List<GrantedAuthority> listAuthority = users.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                .collect(Collectors.toList());
        return new CustomUserDetails(users.getUserId(), users.getUserName(), users.getPassword(), users.getAvatar(), users.getLastName(), users.getFirstName(), users.getEmail(), users.getPhone(), users.getAddress(), users.getUserCreateDate(), users.isUserStatus(),users.getListOrder(),listAuthority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
