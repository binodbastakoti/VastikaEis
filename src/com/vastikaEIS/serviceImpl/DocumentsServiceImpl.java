package com.vastikaEIS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.dao.DocumentDao;
import com.vastikaEIS.domain.Documents;
import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.service.DocumentsService;
import com.vastikaEIS.service.UserService;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class DocumentsServiceImpl implements DocumentsService {

	@Autowired
	private DocumentDao documentDao;

	@Autowired
	private UserService userService;

	public void updateDocument(Documents document, Long documentId, long documentTypeId, long userId) {
		documentDao.updateDocument(document, documentId, documentTypeId, userId);
	}

	public FileUpload saveDocuments(FileUpload fileUpload) {
		System.out.println("*********upload docs docservice Impl*********");
		System.out.println("upload docs docservice Impl" + documentDao.toString());
		return documentDao.saveDocuments(fileUpload);
	}

	@Override
	public long addDocuments(Documents documents, long id) {
		Heroes heroes = userService.findHeroesById(id);
		documents.setHeroes(heroes);
		documents.setStatus(Constant.ACTIV);
		return documentDao.addDocuments(documents);
	}

	@Override
	public long updateDocuments(Documents documents, Documents docFromDB) {
		docFromDB.setDescription(documents.getDescription());
		docFromDB.setDocumentType(documents.getDocumentType());
		docFromDB.setExpiryDate(documents.getExpiryDate());
		return documentDao.addDocuments(docFromDB);
	}

	@Override
	public Documents getDocumentByID(long id) {
		return documentDao.getDocumentByID(id);
	}

	@Override
	public List<Documents> getAllDocuments() {

		return documentDao.getAllDocuments();
	}

	@Override
	public List<Documents> getDocumentByHeroesId(long heroesId) {

		return documentDao.getDocumentByHeroesId(heroesId);
	}

	@Override
	public long deleteDocument(long id) {

		Documents document = getDocumentByID(id);
		document.setStatus(Constant.DELETED);
		return documentDao.addDocuments(document);
	}

}
