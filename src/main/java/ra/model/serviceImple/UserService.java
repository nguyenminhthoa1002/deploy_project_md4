package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Users;
import ra.model.repository.UserRepository;
import ra.model.service.IUserService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = SQLException.class)
public class UserService implements IUserService<Users,Integer> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Users saveOrUpdate(Users users) {
        return userRepository.save(users);
    }

    @Override
    public void delete(Integer id) {
        Users userDelete = findById(id);
        userDelete.setUserStatus(false);
        userRepository.save(userDelete);
    }

    @Override
    public List<Users> searchUser(String searchName) {
        return userRepository.searchUsersByUserNameContaining(searchName);
    }

    @Override
    public Users findUsersByUserName(String userName) {
        return userRepository.findUsersByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<Users> showAllUser() {
        return userRepository.showAllUser();
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
