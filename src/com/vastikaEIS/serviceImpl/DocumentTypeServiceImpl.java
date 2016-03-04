package com.vastikaEIS.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.DocumentTypeDao;
import com.vastikaEIS.domain.DocumentType;
import com.vastikaEIS.service.DocumentTypeService;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService{

	@Autowired
	private DocumentTypeDao documentTypeDao;

	public long addDocumentType(DocumentType documentType){
		documentType.setStatus('A');
		return documentTypeDao.addDocumentType (documentType);
	}

	@Override
	public DocumentType getDocumentTypeById(long id) {
		return documentTypeDao.getDocumentTypeById(id);
	}
	
	@Override
	public long deleteDocumentType(long id)
	{
		DocumentType documentType=documentTypeDao.getDocumentTypeById(id);
		documentType.setStatus('D');
		return documentTypeDao.addDocumentType(documentType);
	}

	@Override
	public List<DocumentType> listAll() {
		
		return documentTypeDao.listAll();
	}
	
}
