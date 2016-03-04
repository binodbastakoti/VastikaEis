package com.vastikaEIS.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.HeroesResume;

@Repository
@Transactional
public class ResumeDao {

	@Autowired
	private SessionFactory sf;

	public long addResume(HeroesResume resume) {
		try {
			long id = (long) sf.getCurrentSession().save(resume);
			if (id > 0) {
				return id;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public List<HeroesResume> getAllResume() {
		try {
			Query str = sf.getCurrentSession().createQuery("from HeroesResume where status=:status");
			str.setParameter("status", Constant.ACTIV);
			return str.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FileUpload findFileUploadById(long id) {
		try {
			FileUpload fileUpload = (FileUpload) sf.getCurrentSession().get(FileUpload.class, id);
			return fileUpload;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<HeroesResume> getResumeByUserId(long heroesId) {
		try {
			Query query = sf.getCurrentSession().createQuery("from HeroesResume where heroesId=:heroesId and status=:status");
			query.setParameter("heroesId", heroesId);
			query.setParameter("status", Constant.ACTIV);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HeroesResume getResumeById(long id) {
		try {
			Query query = sf.getCurrentSession().createQuery("from HeroesResume where id=:id and status=:status");
			query.setParameter("id", id);
			query.setParameter("status", Constant.ACTIV);
			return (HeroesResume) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HeroesResume updateResume(HeroesResume heroResume) {
		try {
			return (HeroesResume) sf.getCurrentSession().merge(heroResume);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
