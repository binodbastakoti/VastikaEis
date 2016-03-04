package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.ResumeMarketingDto;

public interface ResumeMarketingService {

	public List<ResumeMarketing> getAssignedRecruiter(long heroId);

	public boolean assignHero(ResumeMarketingDto marketingDto);
	
	public boolean removeHero(long heroId,long recruiterId);
}
