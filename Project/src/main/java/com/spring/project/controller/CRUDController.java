package com.spring.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.project.dao.ModelDAO;
import com.spring.project.model.Model;
import com.spring.project.model.User;



@Controller
public class CRUDController {
	
	@Autowired
	ModelDAO modelDAO;
	
	@RequestMapping(value="insertForm.htm",method=RequestMethod.GET)
	public String insertForm(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else
			return "model/insert/insertForm";
	}
	
	@RequestMapping(value="insert.htm",method=RequestMethod.POST)
	public String insert(@ModelAttribute("model") Model model, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			long id = modelDAO.insert(model);
			modelMap.addAttribute("id",id);
			modelMap.addAttribute("name", model.getName());
			return "model/insert/insertSuccess";
		}
	}
	
	@RequestMapping(value="updateForm.htm",method=RequestMethod.POST)
	public String update(@RequestParam String id, HttpServletRequest httpServletRequest, ModelMap modelMap) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			Model model = modelDAO.getById(id);
			modelMap.addAttribute("model", model);
			return "model/update/updateForm";
		}
	}
	
	@RequestMapping(value="update.htm",method=RequestMethod.POST)
	public String update(@ModelAttribute Model model, HttpServletRequest httpServletRequest, ModelMap modelMap) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			modelDAO.update(model);
			modelMap.addAttribute("name", model.getName());
			return "model/update/updateSuccess";
		}
	}
	@RequestMapping(value="view.htm",method=RequestMethod.GET)
	public String view(@ModelAttribute("model") Model model, HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		String modelName = httpServletRequest.getParameter("modelName");
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			List<Model> modelList = modelDAO.getList();
			map.addAttribute("modelList",modelList);
			map.addAttribute("modelName", modelName);
			return "model/list";
		}
	}
	
	@RequestMapping(value="delete.htm",method=RequestMethod.POST)
	public String delete(@RequestParam String id, HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			Model model = modelDAO.getById(id);
			modelDAO.delete(model);
			map.addAttribute("modelName",model.getName());
			return "redirect:view.htm";
		}
	}
}
