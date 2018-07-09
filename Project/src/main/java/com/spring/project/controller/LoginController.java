package com.spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.project.dao.LoginDAO;
import com.spring.project.model.User;

@Controller
public class LoginController {

	@Autowired
	LoginDAO loginDAO;
	
	@RequestMapping(value="login/verifylogin.htm", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest, ModelMap map) {
		User verifiedUser = loginDAO.login(user);
		if(verifiedUser != null) {
			HttpSession session = httpServletRequest.getSession();
			session.setAttribute("loggedInUser", verifiedUser);
			if(verifiedUser.getRole().getName().equalsIgnoreCase("Sales Manager")) {
				
				return "redirect:/sales/viewAllOrders.htm";
			}
			else {
				return "redirect:/order/productList.htm";
			}
		}
		else {
			return "login/error";
		}
	}
	
	@RequestMapping(value="login/login.htm")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping(value="logout.htm")
	public String logout(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("loggedInUser");
		return "login/login";
	}
}
