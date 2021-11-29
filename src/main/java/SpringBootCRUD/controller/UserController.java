package SpringBootCRUD.controller;

import SpringBootCRUD.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import SpringBootCRUD.model.User;
import SpringBootCRUD.service.RoleService;
import SpringBootCRUD.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String user(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "/admin")
    public String showAll(@AuthenticationPrincipal User user, @AuthenticationPrincipal Role role, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }


    @PostMapping("/admin/create")
    public String actionAdd(@ModelAttribute User user,
                            @RequestParam("newRoles") String[] roles) {
        Set<Role> allRoles = new HashSet<>();
        for (String role : roles) {
            allRoles.add(roleService.findByRoleName(role));
        }
        user.setRoles(allRoles);
        userService.add(user);
        return "redirect:/admin";
    }

    @PutMapping("/edit/{id}")
    public String upDateUser(@ModelAttribute User user,
                             @RequestParam("editRoles") String[] roles) {
        Set<Role> set = new HashSet<>();
        for (String role : roles) {
            set.add(roleService.findByRoleName(role));
        }
        user.setRoles(set);
        userService.upDateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping(value = "delete/{id}")
    public String actionDelete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;

    }
}