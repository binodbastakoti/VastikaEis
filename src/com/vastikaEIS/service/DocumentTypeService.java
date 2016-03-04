package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.DocumentType;


public interface DocumentTypeService {

	long addDocumentType(DocumentType documentType);
	
	DocumentType getDocumentTypeById(long id);
	public List<DocumentType> listAll();
	long deleteDocumentType(long id);

}
