package com.vastikaEIS.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.Documents;
import com.vastikaEIS.dto.response.DocumentJsonData;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.service.DocumentsService;
import com.vastikaEIS.util.DateTimeFormatter;
import com.vastikaEIS.util.SecurityUtil;
import com.vastikaEIS.util.StringManipulation;

@RestController
@RequestMapping(value = "/api")
public class DocumentsController {
	@Autowired
	private DocumentsService documentService;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/heroes/{heroesId}/documents", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap insertDocument(@Valid @RequestBody Documents document, @PathVariable long heroesId, BindingResult result) throws ParseException {
		long documentId = documentService.addDocuments(document, heroesId);
		JsonMap jsonMap = new JsonMap();

		if (heroesId <= 0) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}

		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailureStatus");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}

		if (documentId > 0) {
			jsonMap.setSuccessStatus();
			jsonMap.setId(documentId);
			jsonMap.setMessages("documentAddedSuccessfully");
			return jsonMap;
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
			return jsonMap;
		}
	}

	@RequestMapping(value = "documents/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap updateDocument(@Valid @RequestBody Documents documents, @PathVariable long id, BindingResult result) throws ParseException {

		Documents doc = documentService.getDocumentByID(id);
		JsonMap jsonMap = new JsonMap();

		if (doc == null) {
			jsonMap.setStatus("failureStatus");
			jsonMap.setMessages("documentNotFound");
			return jsonMap;
		}

		documents.setId(id);
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailureStatus");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}

		long documentId = documentService.updateDocuments(documents, doc);
		if (documentId > 0) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("documentUpdatedSuccessfully");
			return jsonMap;
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
			return jsonMap;
		}
	}

	@RequestMapping(value = "/documents", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Documents> listDocuments() throws ParseException {
		return documentService.getAllDocuments();
	}

	@RequestMapping(value = "/heroes/{heroesId}/documents", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<DocumentJsonData> listHeroesDocuments(@PathVariable long heroesId) throws ParseException {
		List<DocumentJsonData> documentJsonDatas = new ArrayList<DocumentJsonData>();
		List<Documents> documents = documentService.getDocumentByHeroesId(heroesId);
		for (Documents document : documents) {
			DocumentJsonData jsonData = new DocumentJsonData();
			jsonData.setId(document.getId());
			jsonData.setDescription(document.getDescription());
			jsonData.setExpiryDate(DateTimeFormatter.formatDateToString(document.getExpiryDate()));
			jsonData.setDocumentType(document.getDocumentType().getDescription());
			jsonData.setDocumentTypeId(document.getDocumentType().getId());
			String documentName = document.getFileUpload().getRealFileName();
			String extension = StringManipulation.getFileExtension(documentName);
			jsonData.setFileType(extension);
			documentJsonDatas.add(jsonData);
		}
		return documentJsonDatas;
	}

	@RequestMapping(value = "documents/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteDocuments(@PathVariable long id) throws ParseException {
		JsonMap jsonResponseMap = new JsonMap();
		long documentId = documentService.deleteDocument(id);
		if (documentId > 0) {
			jsonResponseMap.setSuccessStatus();
			jsonResponseMap.setMessages("documentDeletedSuccessfully");
			return jsonResponseMap;
		} else {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("failureMsg");
			return jsonResponseMap;
		}
	}

	@RequestMapping(value = "/documents/{documentId}/download", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadDocuments(@PathVariable long documentId, HttpServletResponse response) throws IOException {
		Documents document = documentService.getDocumentByID(documentId);
		String fullPath = document.getFileUpload().getFilePath();
		File file = new File(fullPath);
		HttpHeaders respHeaders = new HttpHeaders();
		response.setContentType("application/force-download");
		respHeaders.setContentLength(file.length());
		respHeaders.setContentDispositionFormData("attachment", document.getFileUpload().getRealFileName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}

}
