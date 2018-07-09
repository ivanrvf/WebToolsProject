package com.spring.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.project.dao.OrderDAO;
import com.spring.project.dao.ProductDAO;
import com.spring.project.model.Order;
import com.spring.project.model.User;
import com.spring.project.model.product.Product;

@Controller
public class OrderController {
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value="order/placeOrder.htm",method=RequestMethod.POST)
	@ResponseBody
	public String placeOrder(HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			HttpSession httpSession = httpServletRequest.getSession();
			Order order = (Order)httpSession.getAttribute("order");
			order.setUser(user);
			Integer savedOrderId = orderDAO.insert(order);
			return "Order is placed. Order id is "+ savedOrderId;
		}
	}
	
	@RequestMapping(value="order/addToCart.htm",method=RequestMethod.POST)
	@ResponseBody
	public String addToCart(@RequestParam String qty,@RequestParam String id, HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			HttpSession httpSession = httpServletRequest.getSession();
			Order order = (httpSession.getAttribute("order") != null)? (Order)httpSession.getAttribute("order"): new Order();
			Product product = productDAO.getById(id);
			Order updatedOrder = orderDAO.addToCart(order,product,Integer.parseInt(qty));
			httpSession.setAttribute("order", updatedOrder);
			return "Successfully Added";
		}
	}
	
	@RequestMapping(value="order/removeCart.htm",method=RequestMethod.POST)
	public String removeCart(@RequestParam String id, HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			HttpSession httpSession = httpServletRequest.getSession();
			Order order = (httpSession.getAttribute("order") != null)? (Order)httpSession.getAttribute("order"): new Order();
			Product product = productDAO.getById(id);
			Order updatedOrder = orderDAO.removeCart(order,product);
			httpSession.setAttribute("order", updatedOrder);
			return "redirect:/order/viewCart.htm";
		}
	}
	
	@RequestMapping(value="order/productList.htm",method=RequestMethod.GET)
	public String productList( HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null) {
			return "login/login";
		}
		else {
			List<Product> productList = productDAO.getList();
			map.addAttribute("productList", productList);
			return "order/productListing";
		}
	}
	
	@RequestMapping(value="/order/viewCart.htm",method=RequestMethod.GET)
	public String viewCart(HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			HttpSession httpSession = httpServletRequest.getSession();
			Order order = (httpSession.getAttribute("order") != null)? (Order)httpSession.getAttribute("order"): null;
			if(order != null) {
				map.addAttribute("order", order);
			}
			else {
				map.addAttribute("order", null);
			}
			return "order/viewCart";
		}
	}
	
	@RequestMapping(value="/order/viewOrders.htm",method=RequestMethod.GET)
	public String viewAllOrder(HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			List<Order> orderList = orderDAO.getListByUser(user);
			if(orderList != null) {
				map.addAttribute("orderList", orderList);
			}
			else {
				map.addAttribute("orderList", null);
			}
			return "order/viewOrders";
		}
	}
}
