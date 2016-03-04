package com.vastikaEIS.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.dao.ResumeDao;
import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.HeroesResume;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.ResumeService;
import com.vastikaEIS.service.UserService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private UserService userService;

	public long addResume(HeroesResume resume, long userId) {
		Heroes heroes = userService.findHeroesById(userId);
		resume.setHeroes(heroes);
		resume.setStatus(Constant.ACTIV);
		return resumeDao.addResume(resume);
	}

	@Override
	public List<HeroesResume> getAllResume() {
		return resumeDao.getAllResume();
	}

	@Override
	public FileUpload findFileUploadById(long id) {
		return resumeDao.findFileUploadById(id);
	}

	@Override
	public List<HeroesResume> getResumeByUserId(long heroesId) {
		return resumeDao.getResumeByUserId(heroesId);
	}

	@Override
	public HeroesResume getResumeById(long id) {
		return resumeDao.getResumeById(id);
	}

	@Override
	public HeroesResume updateResume(HeroesResume heroesResume, long id, User applicationUser) {
		HeroesResume prevHeroesResume = getResumeById(id);
		prevHeroesResume.setEmail(heroesResume.getEmail());
		prevHeroesResume.setResumeContact(heroesResume.getResumeContact());
		prevHeroesResume.setTitle(heroesResume.getTitle());
		prevHeroesResume.setVisibility(heroesResume.getVisibility());
		prevHeroesResume.setModifiedBy(applicationUser);
		prevHeroesResume.setModifiedDate(new Date());
		return resumeDao.updateResume(prevHeroesResume);
	}

	public HeroesResume deleteResume(long id) {
		HeroesResume heroesResume = getResumeById(id);
		heroesResume.setStatus(Constant.DELETED);
		return resumeDao.updateResume(heroesResume);
	}
}
