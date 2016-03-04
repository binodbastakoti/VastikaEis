package com.vastikaEIS.dto.request;

import java.io.Serializable;
import java.util.List;

public class ResumeMarketingDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long recruiterId;
	private Long heroId;
	private List<Long> resumeIds;

	public Long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public Long getHeroId() {
		return heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}

	public List<Long> getResumeIds() {
		return resumeIds;
	}

	public void setResumeIds(List<Long> resumeIds) {
		this.resumeIds = resumeIds;
	}

}
