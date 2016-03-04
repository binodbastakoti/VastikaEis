package com.vastikaEIS.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.HeroesResume;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.ResumeMarketingDto;
import com.vastikaEIS.dto.response.JsonMap;

@Repository
@Transactional
public class ResumeMarketingDao {
	@Autowired
	private SessionFactory sf;

	public List<ResumeMarketing> getAssignedRecruiter(long heroId) {
		try {
			Query query = sf.getCurrentSession().createQuery("from ResumeMarketing rm where rm.heroes.id=:heroId");
			query.setParameter("heroId", heroId);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean assignHero(ResumeMarketingDto marketingDto) {
		try {
			List<Long> resumeIds = marketingDto.getResumeIds();
			Long recruiterId = marketingDto.getRecruiterId();
			User user = (User) sf.getCurrentSession().get(User.class, recruiterId);
			Long heroId = marketingDto.getHeroId();
			Heroes heroes = (Heroes) sf.getCurrentSession().get(Heroes.class, heroId);
			for (Long id : resumeIds) {
				HeroesResume heroesResume = (HeroesResume) sf.getCurrentSession().get(HeroesResume.class, id);
				ResumeMarketing resumeMarketing = new ResumeMarketing();
				resumeMarketing.setRecruiter(user);
				resumeMarketing.setHeroes(heroes);
				resumeMarketing.setHeroResume(heroesResume);
				sf.getCurrentSession().save(resumeMarketing);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeHero(long heroId, long recruiterId) {
		try {
			Query query = sf.getCurrentSession().createQuery(
					"delete from ResumeMarketing rm where rm.heroes.id=:heroId and rm.recruiter.id=:recruiterId");
			query.setParameter("heroId", heroId);
			query.setParameter("recruiterId", recruiterId);
			query.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
