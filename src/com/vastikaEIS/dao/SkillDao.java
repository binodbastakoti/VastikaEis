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
public class SkillDao {

	@Autowired
	private SessionFactory sf;

	public long addSkill(Skill skill) {

		Skill newSkill = (Skill) sf.getCurrentSession().merge(skill);
		if (newSkill != null) {
			return newSkill.getId();
		}
		return 0;
	}

	public List<Skill> listAll() {
		Query str = sf.getCurrentSession().createQuery("from Skill where status='A'");
		return str.list();
	}

	public Skill findSkillByID(long id) {
		// return (Skill) sf.getCurrentSession().load(Skill.class, id);
		Query str = sf.getCurrentSession().createQuery("from Skill where id=:id and status='A'");
		str.setParameter("id", id);

		return (Skill) str.uniqueResult();
	}

	public SkillCategory findCategoryById(long skillCategoryId) {
		try {
			Query str = sf.getCurrentSession().createQuery("from SkillCategory where id=:id and status='A'");
			str.setParameter("id", skillCategoryId);
			return (SkillCategory) str.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
