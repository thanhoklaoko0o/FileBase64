package com.cuong.phonestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.OnepayResponse;

@Repository
public interface OnepayResponseRepository extends CrudRepository<OnepayResponse, String>{

}
