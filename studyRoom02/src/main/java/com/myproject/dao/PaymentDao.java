package com.myproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.myproject.domain.FeeDomain;
import com.myproject.domain.PaymentDomain;

@Mapper
public interface PaymentDao {

	int insert(PaymentDomain paymentDomain);
	
	FeeDomain selectFee(Integer fno);
	
}
