package SpringBootCRUD.service;

import SpringBootCRUD.model.Role;

import java.util.List;


public interface RoleService {
    void add(Role roleName);

    void upDateRole(Role roleName);

    void delete(Long id);

    Role findByRoleName(String roleName);

    List<Role> getAllRoles();

    boolean existsByName(String name);
}
