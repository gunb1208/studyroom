package com.myproject.service;

import com.myproject.domain.FeeDomain;
import com.myproject.domain.PaymentDomain;
import com.myproject.domain.RegInfoDomain;

public interface PaymentService {

	// 결제
	int pay(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain);
	
	FeeDomain findBy(Integer fno); 
	
	// 기간 연장 결제
	void extendPayment(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain);
		
}
