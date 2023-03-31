package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.ERole;
import ra.model.entity.Roles;
import ra.model.repository.RoleRepository;
import ra.model.service.IRoleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = SQLException.class)
public class RoleService implements IRoleService<Roles,Integer> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles findById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Roles saveOrUpdate(Roles roles) {
        return roleRepository.save(roles);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
