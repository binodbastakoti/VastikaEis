package com.vastikaEIS.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;



@Repository
@Transactional
public class SkillCategoryDao {

	@Autowired
	private SessionFactory sf;

	public long addSkillCategory(SkillCategory skillCategory) {

		SkillCategory newSkillCategory  = (SkillCategory) sf.getCurrentSession().merge(skillCategory);
		if (newSkillCategory != null) {
			return newSkillCategory.getId();
		}
		return 0;

	}

	public List<SkillCategory> listAll() {
		Query str = sf.getCurrentSession().createQuery("from SkillCategory where status='A'");
		return str.list();
	}

	public SkillCategory getSkillCategoryById(long id) {

		Query str = sf.getCurrentSession().createQuery("from SkillCategory where id=:id and status='A'");
		str.setParameter("id", id);

		return (SkillCategory) str.uniqueResult();
	}

	public List<Skill> getAllSkillsByCatId(long id) {
		Query str = sf.getCurrentSession().createQuery("from Skill where skillCategory_id=:id and status='A'");
		str.setParameter("id", id);
		return str.list();
	}

}
