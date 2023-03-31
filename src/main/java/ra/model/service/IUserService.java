package ra.model.service;


import ra.model.entity.Users;

import java.util.List;

public interface IUserService<T,V> extends IShopService<T,V> {
    List<T> searchUser(String searchName);
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    List<Users> showAllUser();
    Users findByEmail(String email);
}
