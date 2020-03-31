package com.cuong.phonestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.OnepayRequest;

@Repository
public interface OnepayRequestRepository extends CrudRepository<OnepayRequest, Long>{

}
