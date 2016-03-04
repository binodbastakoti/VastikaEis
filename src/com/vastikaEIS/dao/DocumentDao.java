package com.vastikaEIS.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.domain.DocumentType;
import com.vastikaEIS.domain.Documents;
import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.UserService;

@Transactional
@Repository
public class DocumentDao {

	@Autowired
	private SessionFactory sf;
	@Autowired
	private UserService userService;

	public void updateDocument(Documents document, long documentId, long documentTypeId, long userId) {
		User user = userService.findUserById(userId);
		DocumentType docType = (DocumentType) sf.getCurrentSession().get(DocumentType.class, documentTypeId);
		document.setDocumentType(docType);
		sf.getCurrentSession().merge(document);
	}

	public FileUpload saveDocuments(FileUpload fileUpload) {
		FileUpload fUpload = (FileUpload) sf.getCurrentSession().merge(fileUpload);
		return fUpload;

	}

	public long addDocuments(Documents documents) {
		try {
			Documents newDocument = (Documents) sf.getCurrentSession().merge(documents);
			if (newDocument != null) {
				return newDocument.getId();
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Documents getDocumentByID(long id) {
		Query str = sf.getCurrentSession().createQuery("from Documents where id=:id");
		str.setParameter("id", id);

		return (Documents) str.uniqueResult();
	}

	public List<Documents> getAllDocuments() {
		try {
			Query str = sf.getCurrentSession().createQuery("from Documents");
			return str.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Documents> getDocumentByHeroesId(long heroesId) {
		try {
			Query query = sf.getCurrentSession().createQuery("from Documents d where d.heroes.id=:heroesId and d.status=:status");
			query.setParameter("heroesId", heroesId);
			query.setParameter("status", Constant.ACTIV);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
