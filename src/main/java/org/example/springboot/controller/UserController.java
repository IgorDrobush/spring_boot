package org.example.springboot.controller;

import jakarta.validation.Valid;
import org.example.springboot.model.User;
import org.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String getAllUsers(ModelMap model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}

	@GetMapping(value = "/save_user_form")
	public String saveUserForm(ModelMap model) {
		model.addAttribute("user", new User());
		return "save_user_form";
	}

	@PostMapping(value = "/save")
	public String saveUser(
			@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return "save_user_form";
		}

		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "/delete")
	public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
		userService.deleteUserById(id);
		return "redirect:/";
	}

	@GetMapping(value = "/update_user_form")
	public String updateUserForm(
			@RequestParam(value = "id", required = false) Long id,
			ModelMap model
	) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "update_user_form";
	}

	@PostMapping(value = "/update")
	public String updateUser(
			@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return "update_user_form";
		}

		userService.updateUser(user);
		return "redirect:/";
	}
}