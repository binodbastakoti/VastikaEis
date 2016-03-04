package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.HeroesResume;
import com.vastikaEIS.domain.User;

public interface ResumeService {

	public long addResume(HeroesResume resume, long userId);

	public List<HeroesResume> getAllResume();

	public FileUpload findFileUploadById(long id);

	public List<HeroesResume> getResumeByUserId(long heroesId);

	public HeroesResume getResumeById(long id);

	public HeroesResume updateResume(HeroesResume heroesResume, long id,User applicationUser);
	
	public HeroesResume deleteResume(long id);
}
