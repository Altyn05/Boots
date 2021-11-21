package SpringBootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import SpringBootCRUD.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}