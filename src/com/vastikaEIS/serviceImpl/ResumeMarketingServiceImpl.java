package com.vastikaEIS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.ResumeMarketingDao;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.dto.request.ResumeMarketingDto;
import com.vastikaEIS.service.ResumeMarketingService;

@Service
public class ResumeMarketingServiceImpl implements ResumeMarketingService {
	@Autowired
	private ResumeMarketingDao marketingDao;

	public List<ResumeMarketing> getAssignedRecruiter(long heroId) {
		return marketingDao.getAssignedRecruiter(heroId);
	}

	@Override
	public boolean assignHero(ResumeMarketingDto marketingDto) {
		return marketingDao.assignHero(marketingDto);
	}

	public boolean removeHero(long heroId,long recruiterId) {
		return marketingDao.removeHero(heroId,recruiterId);
	}

}
