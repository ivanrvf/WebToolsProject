package com.spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {
	
	@RequestMapping(value="")
	public String listing(@ModelAttribute("Model") HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		return "test";
	}
}
