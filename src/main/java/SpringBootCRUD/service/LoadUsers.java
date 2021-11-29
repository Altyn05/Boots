package SpringBootCRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import SpringBootCRUD.model.Role;
import SpringBootCRUD.model.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class LoadUsers {

    UserService userService;
    RoleService roleService;


    @Autowired
    public LoadUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void forTestUsers() {

        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        roleService.add(role1);
        roleService.add(role2);

        User user1 = new User("Elton", "Jon", "user1@mail.ru", "1");
        User user2 = new User("Alla",  "Pugacheva", "user2@mail.ru", "1");
        User user3 = new User("Filip", "Kirkorov", "user3@mail.ru", "1");

        user1.setRoles(Set.of(role2,role1));
        user2.setRoles(Set.of(role1));
        user3.setRoles(Set.of(role1));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
    }

}
