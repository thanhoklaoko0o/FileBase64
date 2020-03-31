package com.cuong.phonestore.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.model.OrderDetails;
import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.model.User;
import com.cuong.phonestore.repository.OrderRepository;
import com.cuong.phonestore.repository.ProductRepository;
import com.cuong.phonestore.repository.UserRepository;
import com.cuong.phonestore.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

	@Override
	public Integer getAllOrdersCount(Principal principal) {
		User user = getUserFromPrinciple(principal);
        return orderRepository.countAllByUser(user);
	}

	@Override
	public List<Order> getAllOrdersByUserOrderByOrderDateDesc(Principal principal, Integer page, Integer pageSize) {
		 User user = getUserFromPrinciple(principal);
	        if (page == null || page < 0) {
	            throw new IllegalArgumentException("Invalid page");
	        }
        return orderRepository.findAllByUserOrderByOrderDateDesc(user, PageRequest.of(page, pageSize));
	}

	@Override
	public Order postOrder(Principal principal, Order order, ArrayList<Product> cart) {
		User user = getUserFromPrinciple(principal);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setOrderDetailsList(new ArrayList<>());
        
        Product product = new Product();
        for (Product cartItem : cart) {
            //increase sell count on the product
            //cartItem.getCartProduct().setSellCount(i.getCartProduct().getSellCount() + i.getAmount());
        	product = productRepository.findByProductID(cartItem.getProductID());
            if(product.getQuantity() > 0) {
            	if(cartItem.getQuantity() > product.getQuantity()) {
            		cartItem.setQuantity(product.getQuantity());
            	}
            	OrderDetails orderDetails = new OrderDetails();
            	orderDetails.setAmount(cartItem.getQuantity());
                orderDetails.setOrder(order);
                orderDetails.setPrice(cartItem.getPrice());
                orderDetails.setProduct(cartItem);
                order.getOrderDetailsList().add(orderDetails);
                product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            	productRepository.save(product);
            }
        }
        orderRepository.save(order);
        return order;
	}
	
	 private User getUserFromPrinciple(Principal principal) {
	        if (principal == null || principal.getName() == null) {
	            throw new IllegalArgumentException("Invalid access");
	        }
	        Optional<User> user = userRepository.findByUsername(principal.getName());//principal.getName()
	        if (user == null) {
	            throw new IllegalArgumentException("User not found");
	        }
	        return user.isPresent() ? (User) user.get() : null;
	    }

	@Override
	public Order editOrder(Order order) {
		Order orderOutput = orderRepository.save(order);
		return orderOutput;
	}

	@Override
	public Order getOrderById(String orderID) {
		return orderRepository.findById(orderID).isPresent() ? (Order) orderRepository.findById(orderID).get() : null;
	}

	@Override
	public Page<Order> findAllOrderByOrderDate(Integer page, Integer pageSize) {
		if (page == null || page < 0) {
            throw new IllegalArgumentException("Invalid page");
        }
		return orderRepository.findAll(PageRequest.of(page, pageSize));
	}

}
