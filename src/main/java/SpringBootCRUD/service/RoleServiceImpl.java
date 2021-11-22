package SpringBootCRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import SpringBootCRUD.model.Role;
import SpringBootCRUD.repository.RoleRepository;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role roleName) {
        roleRepository.save(roleName);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void upDateRole(Role roleName) {
        roleRepository.save(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        try {
            return findByRoleName(name).getRoleName().equals(name);
        } catch (Exception e) {
            return false;
        }
    }

//    @Override
//    public boolean existsByName(String name) {
//        return roleRepository.(name);
//    }

}
