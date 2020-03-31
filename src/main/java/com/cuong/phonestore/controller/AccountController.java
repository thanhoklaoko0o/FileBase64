package com.cuong.phonestore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cuong.phonestore.services.impl.UserDetailsServiceImpl;

@Controller
@RequestMapping("/login")
public class AccountController {
	
	@Autowired
	private UserDetailsServiceImpl userService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String login(@RequestParam(required = false) String message, Model model, Principal principal) {
		if (message != null && !message.isEmpty()) {
			if (message.equals("logout")) {
				model.addAttribute("message", "Logout!");
			}
			if (message.equals("error")) {
				model.addAttribute("message", "Login Failed!");
			}
		} else if(userService.getUserFromPrinciple(principal) != null) {
			return "redirect:/";
		}
		return "login";
	}
}
