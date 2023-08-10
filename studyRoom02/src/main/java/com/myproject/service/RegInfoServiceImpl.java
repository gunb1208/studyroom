package com.myproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.RegInfoDao;
import com.myproject.dao.SeatDao;
import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class RegInfoServiceImpl implements RegInfoService {
	
	private RegInfoDao regInfoDao;
	private SeatDao seatDao;
	
	@Override
	@Transactional
	public int RegisterRegInfo(RegInfoDomain regInfoDomain) {
		log.info("Service:: RegisterRegInfo");
		/* 등록 완료 시 좌석의 상태를 '이용중'으로 변경하기 위한 작업 */
		SeatDomain seatDomain = new SeatDomain();
		seatDomain.setUserNo(regInfoDomain.getUserNo());
		seatDomain.setStatus(true);
		seatDomain.setSno(regInfoDomain.getSeatNo());
		seatDao.updateStatus(seatDomain);
		
		return regInfoDao.insertRegInfo(regInfoDomain);
	}

	@Override
	public RegInfoDomain read(int userNo) {
		return regInfoDao.getDetail(userNo);
	}

	@Override
	public List<RegInfoDomain> getAllList() {
		return regInfoDao.getAllList();
	}
	
	@Override
	@Transactional
	public void changeSeat(RegInfoDomain regInfoDomain, SeatDomain seatDomain) {
		
		regInfoDao.updateSeat(regInfoDomain);
		
		/* 변경할 좌석과 기존 이용 좌석의 상태 값을 바꾸는 작업 */
		seatDao.updateNewSeat(seatDomain);
		seatDao.updateOldSeat(seatDomain);
		
	}
	
	@Override
	public int extendUsingPeriod(RegInfoDomain regInfoDomain) {
		return regInfoDao.updatePeriod(regInfoDomain);
	}
	
}
