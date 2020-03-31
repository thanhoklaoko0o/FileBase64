package com.cuong.phonestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.OnepayRequest;
import com.cuong.phonestore.repository.OnepayRequestRepository;
import com.cuong.phonestore.services.OnepayRequestService;

@Service
public class OnepayRequestServiceImpl implements OnepayRequestService{

	@Autowired
	private OnepayRequestRepository onepayRequestRepository;

	@Override
	public OnepayRequest save(OnepayRequest onepayRequest) {
		return onepayRequestRepository.save(onepayRequest);
	}
}
