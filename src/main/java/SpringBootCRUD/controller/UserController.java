package SpringBootCRUD.controller;

import SpringBootCRUD.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import SpringBootCRUD.model.User;
import SpringBootCRUD.service.RoleService;
import SpringBootCRUD.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


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

	@GetMapping(value = "/hello")
	public String hello(Model model) {
		model.addAttribute("users", "hello");
		return "hello";
	}

	@GetMapping(value = "/user")
	public String user(Model model, HttpTrace.Principal principal) {
		model.addAttribute("user", userService.getByUsername(principal.getName()));
		return "user";
	}

	@GetMapping(value = "/admin")
	public String showAll(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "all";
	}

	@GetMapping(value = "/admin/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "add";
	}

		@PostMapping("admin/add/")
	public String actionAdd(@ModelAttribute("user") User user,
							@RequestParam("roles") String role) {
		if (roleService.existsByName(role)) {
			Role tmp = roleService.findByRoleName(role);
			HashSet<Role> set = new HashSet<>();
			set.add(tmp);
			user.setRoles(set);
			userService.add(user);
		}
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/edit")
	public String edit(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
		if (id == -1) {
			return "redirect:/admin";
		}
		User user = userService.getUserById(id);
		if (user == null) {
			return "redirect:/admin";
		}
		model.addAttribute("user", user);

		return "edit";
	}

	@PatchMapping("admin/{id}")
	public String actionEdit(@ModelAttribute("user") User user) {
		userService.upDateUser(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "admin/delete")
	public String actionDelete(@RequestParam(value = "id", defaultValue = "-1") long id) {
		userService.delete(id);
		return "redirect:/admin";
	} 
//
//	@GetMapping(value = "hello")
//	public String printWelcome(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("I'm Spring MVC-SECURITY application");
//		messages.add("5.2.0 version by sep'19 ");
//		model.addAttribute("messages", messages);
//		return "hello";
//	}


}