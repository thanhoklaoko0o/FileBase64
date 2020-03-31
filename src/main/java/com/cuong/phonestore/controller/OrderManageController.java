package com.cuong.phonestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.services.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderManageController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * 
	 * @param principal
	 * @return count order
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllOrdersCount(Principal principal) {
        Integer orderCount = orderService.getAllOrdersCount(principal);
        return new ResponseEntity<Integer>(orderCount, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer pageSize) {
        Page<Order> orderList = orderService.findAllOrderByOrderDate(page, pageSize);
        return new ResponseEntity<List<Order>>(orderList.getContent(), HttpStatus.OK);
    }
    
  //update pay status for order
  	@RequestMapping(value="/Confirm/{orderID}", method=RequestMethod.GET)
  	@PreAuthorize("hasRole('ADMIN')")
  	public ResponseEntity<Order> confirmPaymentStatus(@PathVariable("orderID") String orderID) {
  		Order order = orderService.getOrderById(orderID);
  		order.setPaid(0);
  		orderService.editOrder(order);
  		return new ResponseEntity<Order>(order, HttpStatus.OK);
  	}
}
