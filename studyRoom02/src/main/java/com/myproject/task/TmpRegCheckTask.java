package com.myproject.task;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.RegInfoDao;
import com.myproject.dao.SeatDao;
import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class TmpRegCheckTask {
	
	private RegInfoDao regInfoMapper;
	private SeatDao seatMapper;

	@Transactional
	@Scheduled(cron="0 10 * * * *")
	public void checkPeriod() throws Exception {
		
		log.warn("임시 등록 task 실행...");
		log.warn("========================================");
		List<RegInfoDomain> tmpToRegList = regInfoMapper.getTmpToRegList();
		if(tmpToRegList.size() == 0	) return;

		for(RegInfoDomain tmp : tmpToRegList) {
			
			SeatDomain seatDomain = new SeatDomain();
			seatDomain.setSno(tmp.getSeatNo());
			seatDomain.setStatus(false);
			seatDomain.setUserNo((Integer) null);
			seatMapper.updateStatus(seatDomain);
			
		}
		regInfoMapper.deleteTmpReg();
	}
}
