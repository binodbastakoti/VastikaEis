package com.vastikaEIS.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.DocumentType;

@Repository
@Transactional
public class DocumentTypeDao {
	@Autowired
	private SessionFactory sf;
	
	public long addDocumentType(DocumentType documentType){
		DocumentType newDocumentType=(DocumentType)sf.getCurrentSession().merge(documentType);
		if(newDocumentType!=null){
			return newDocumentType.getId();
		}
			return 0;	
	}
	
	public DocumentType getDocumentTypeById(long id){
		return (DocumentType) sf.getCurrentSession().get(DocumentType.class, id);
	}
	
	public List<DocumentType> listAll()
	{
		Query str=sf.getCurrentSession().createQuery("from DocumentType where status ='A'");
		return str.list();
			
	}
	

}
