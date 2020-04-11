package com.dagtech.springcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dagtech.springcrud.entity.User;
import com.dagtech.springcrud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//show the user main page
	@GetMapping("/userPage")
	public String showUserPage(Model model) {
		
        List <User> users = userService.getUsers();
		
		model.addAttribute("users", users);
		
		return "user";
	}
	
	// show the user form page
	@GetMapping("/userForm")
	public String showUserForm(Model model) {
		
        User user = new User();
		
		model.addAttribute("user", user);
		
		return "user-form";
	}
	
	// save the user and redirect to user main page
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		
		userService.saveUser(user);
		
		return "redirect:/user/userPage";
		
	}
	
	// redirect to user page form to update the fields
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int id,
			                         Model model) {
		
		User user = userService.getUser(id);
		
		model.addAttribute("user", user);
		
		return "user-form";
	}
	
	// delete an user created after show you a warning dialog
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") int id) {
		
		userService.deleteUser(id);
		
		return "redirect:/user/userPage";
	}
	
	// search a user created by first name or last name 
	@GetMapping("/searchUser")
	public String searchUsers(@RequestParam("searchUname")
	                             String searchUname, Model model) {
		
		List<User> users =
				userService.searchUsers(searchUname);
		
		model.addAttribute("users", users);
		
		return "user";
		
	}
	
	

}
