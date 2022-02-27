package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.MyUserRepository;
import com.smart.entities.MyUser;
import com.smart.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private MyUserRepository myUserRepository;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-Smart Contact Manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("user", new MyUser());
		return "signup";
	}

//	handler for registering user

	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") MyUser myUser, BindingResult result,
			@RequestParam(value = "aggrement", defaultValue = "false") boolean aggrement, Model model,
			HttpSession session) {

		try {

			if (!aggrement) {
				System.out.println("you have not checked the aggrement");
//				exception aagai to neeche wala block chal jaega
				throw new Exception("you have not checked the aggrement");
			}

			if (result.hasErrors()) {

				model.addAttribute("user", myUser);
				System.out.println("error " + result.toString());
				return "signup";
			}

			myUser.setRole("ROLE_USER");
			myUser.setEnabled(true);
			myUser.setImageUrl("default.png");

			System.out.println("Aggrement" + aggrement);
			System.out.println("user" + myUser);

			MyUser user = myUserRepository.save(myUser);

			model.addAttribute("user", new MyUser());
			session.setAttribute("message", new Message("Successfully Registered !! ", "alert-success"));
			return "signup";

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("user", myUser);
			session.setAttribute("message", new Message("Something went wrong !! " + e.getMessage(), "alert-danger"));
			return "signup";

		}

	}

}
