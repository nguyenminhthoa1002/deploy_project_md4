package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.Users;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    List<Users> searchUsersByUserNameContaining(String searchName);
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    @Query(value = "select * from user where userId!=1",nativeQuery = true)
    List<Users> showAllUser();
    Users findByEmail(String email);
}
