package SpringBootCRUD.service;

import SpringBootCRUD.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void add(User username);

    void delete(Long id);

//    User getUserByName(String username);

    User getByUsername(String username);

    User getUserById(long id);

    void upDateUser(User username);

    List<User> getAllUsers();
}
