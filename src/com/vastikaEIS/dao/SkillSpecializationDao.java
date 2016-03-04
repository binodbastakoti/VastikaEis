package com.vastikaEIS.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.SkillSpecialization;

@Repository
public class SkillSpecializationDao {

	@Autowired
	private SessionFactory sf;

	public boolean addSkillSpecialization(
			SkillSpecialization skillSpecialization, SkillCategory skillCategory) {
//		sf.getCurrentSession().persist(skillCategory);
		
		long id = (long) sf.getCurrentSession().save(skillSpecialization);
		if (id > 0) {
			return true;
		}
		return false;

	}

}
