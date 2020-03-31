package com.cuong.phonestore.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.model.Product;

public interface OrderService {
	Integer getAllOrdersCount(Principal principal);

    List<Order> getAllOrdersByUserOrderByOrderDateDesc(Principal principal, Integer page, Integer pageSize);

    Order postOrder(Principal principal, Order order, ArrayList<Product> cart);
    
    Order editOrder(Order order);
    
    Order getOrderById(String orderID);
    
    Page<Order> findAllOrderByOrderDate(Integer page, Integer pageSize);
}
