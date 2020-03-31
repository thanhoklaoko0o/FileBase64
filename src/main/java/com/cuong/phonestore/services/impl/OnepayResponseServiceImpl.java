package com.cuong.phonestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.OnepayResponse;
import com.cuong.phonestore.repository.OnepayResponseRepository;
import com.cuong.phonestore.services.OnepayResponseService;

@Service
public class OnepayResponseServiceImpl implements OnepayResponseService{

	@Autowired
	private OnepayResponseRepository onepayResponseRepository;

	@Override
	public OnepayResponse save(OnepayResponse onepayResponse) {
		return onepayResponseRepository.save(onepayResponse);
	}
}
