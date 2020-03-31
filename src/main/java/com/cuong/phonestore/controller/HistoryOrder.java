package com.cuong.phonestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.services.OrderService;

@Controller
@RequestMapping("/historyOrder")
public class HistoryOrder {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
    public String getAllOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer pageSize,
    		Principal principal, Model model) {
        List<Order> orderList = orderService.getAllOrdersByUserOrderByOrderDateDesc(principal, page, pageSize);
        model.addAttribute("orders", orderList);
        return "historyOrder";
    }
}
