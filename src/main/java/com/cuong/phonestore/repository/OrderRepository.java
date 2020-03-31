package com.cuong.phonestore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.Order;
import com.cuong.phonestore.model.User;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String>{

	List<Order> findAllByUserOrderByOrderDateDesc(User user, Pageable pageable);

    Integer countAllByUser(User user);
    
}
