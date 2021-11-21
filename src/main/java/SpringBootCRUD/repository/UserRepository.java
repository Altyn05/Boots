package SpringBootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import SpringBootCRUD.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);

}
