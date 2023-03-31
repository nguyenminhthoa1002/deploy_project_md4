package ra.model.service;

import ra.model.entity.ERole;
import ra.model.entity.Roles;

import java.util.Optional;

public interface IRoleService<T,V> extends IShopService<T,V>{
    Optional<Roles> findByRoleName(ERole roleName);
}
