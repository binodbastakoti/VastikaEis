package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Documents;
import com.vastikaEIS.domain.FileUpload;

public interface DocumentsService {

	public void updateDocument(Documents document, Long documentId, long documentTypeId, long userId);

	public FileUpload saveDocuments(FileUpload fileUpload);

	public long addDocuments(Documents documents, long heroesId);

	public long updateDocuments(Documents documents, Documents docFromDB);

	public Documents getDocumentByID(long id);

	public List<Documents> getAllDocuments();

	public List<Documents> getDocumentByHeroesId(long heroesId);

	public long deleteDocument(long id);

}
