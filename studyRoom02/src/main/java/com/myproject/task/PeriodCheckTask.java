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
public class PeriodCheckTask {
	
	private RegInfoDao regInfoMapper;
	private SeatDao seatMapper;
	
	@Transactional
	@Scheduled(cron="0 30 23 * * *")
	public void checkPeriod() throws Exception {
		
		log.warn("테스크 실행...");
		log.warn("========================================");
		List<RegInfoDomain> regList = regInfoMapper.getExpireInfo();
		if(regList.size() == 0) return;
		
		regList.forEach(log::info);
		for(RegInfoDomain vo: regList) {
			SeatDomain seatDomain = new SeatDomain();
			seatDomain.setSno(vo.getSeatNo());
			seatDomain.setStatus(false);
			seatDomain.setUserNo(null);
			
			seatMapper.updateStatus(seatDomain);
			log.warn(seatDomain);
		}
		regInfoMapper.deleteRegInfo();
	}
}
