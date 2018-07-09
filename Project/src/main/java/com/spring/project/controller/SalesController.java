package com.spring.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.dao.OrderDAO;
import com.spring.project.model.Order;
import com.spring.project.model.User;

@Controller
public class SalesController {

	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping(value="/sales/viewAllOrders.htm",method=RequestMethod.GET)
	public String viewAllOrder(HttpServletRequest httpServletRequest, ModelMap map) {
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		if(user == null)
			return "login/login";
		else {
			int pageNo = 0;
			if( httpServletRequest.getParameter("pageNo") != null ) {
				pageNo = Integer.parseInt(httpServletRequest.getParameter("pageNo"));
			}
			else {
				pageNo = 1;
			}
			int limit = 50;
			int offset = (pageNo * limit) - limit;
			List<Order> orderList = orderDAO.get(offset, limit);
			if(orderList != null) {
				map.addAttribute("currentPage", pageNo);
				map.addAttribute("orderList", orderList);
			}
			else {
				map.addAttribute("orderList", null);
			}
			return "sales/viewAllOrders";
		}
	}
	
	@RequestMapping(value="/sales/excel.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView  exportToExcel(HttpServletRequest request, ModelMap map) {
		int pageNo = 0;
		if( request.getParameter("pageNo") != "" ) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		else {
			pageNo = 1;
		}

		int limit = 50;
		int offset = (pageNo * limit) - limit;
		List <Order> salesOrderList = orderDAO.get(offset, limit);
		Map<String,List<Order>> salesOrderMap = new HashMap<String,List<Order>>();
			salesOrderMap.put("SalesOrders", salesOrderList);
		return new ModelAndView("excelView","salesOrderMap",salesOrderMap);
	}
}
