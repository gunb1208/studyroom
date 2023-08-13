package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.SeatDao;
import com.myproject.domain.SeatDomain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatDao seatDao;
	
	@Override
	public List<SeatDomain> getAllList() {
		log.info("Service:: getAllList");
		return seatDao.getAllList();
	}

	@Override
	public List<SeatDomain> getEmptySeatList() {
		log.info("Service:: getEmptySeatList");
		return seatDao.getEmptySeatList();
	}

	@Override
	public void changeStatus(SeatDomain seatDomain) {
		log.info("Service:: changeStatus");
		seatDao.updateStatus(seatDomain);
	}

}
