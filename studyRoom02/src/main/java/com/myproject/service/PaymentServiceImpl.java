package com.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.PaymentDao;
import com.myproject.dao.RegInfoDao;
import com.myproject.dao.SeatDao;
import com.myproject.domain.FeeDomain;
import com.myproject.domain.PaymentDomain;
import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private RegInfoDao reginfoDao;
	@Autowired
	private SeatDao seatDao;
	
	@Override
	@Transactional
	public int pay(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain) {
		
		reginfoDao.insertRegInfo(regInfoDomain);
		
		int regno = regInfoDomain.getRegNo();
		log.info("regno..." + regno);
		
		paymentDomain.setRegNo(regno);
		log.info("paymentDomain" + paymentDomain);
		
		SeatDomain seatDomain = new SeatDomain();
		seatDomain.setUserNo(regInfoDomain.getUserNo());
		seatDomain.setStatus(true);
		seatDomain.setSno(regInfoDomain.getSeatNo());
		
		seatDao.updateStatus(seatDomain);
		
		paymentDao.insert(paymentDomain);
		
		return regno;
	}

	@Transactional
	@Override
	public void extendPayment(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain) {
		
		regInfoDomain.setUserNo(regInfoDomain.getUserNo()); // 등록정보에서 유저넘 가져오기
		reginfoDao.updatePeriod(regInfoDomain); // userNo, period
		
		paymentDomain.setUserNo(regInfoDomain.getUserNo());
		paymentDomain.setRegNo(regInfoDomain.getRegNo());
		
		paymentDao.insert(paymentDomain);
		
	}

	@Override
	public FeeDomain findBy(Integer fno) {
		
		log.info("findBy..."+fno);
		return paymentDao.selectFee(fno);
	}
	
	
}
