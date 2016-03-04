package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.DocumentType;
import com.vastikaEIS.dto.response.DocumentTypeJson;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.service.DocumentTypeService;

@RestController
@RequestMapping("/api")
public class DocumentTypeController {

	@Autowired
	private DocumentTypeService documentTypeService;

	@RequestMapping(value = "/documentType", method = RequestMethod.POST, headers = "Accept=Application/json")
	public JsonMap addDocumentType(@Valid @RequestBody DocumentType documentType, BindingResult result) throws ParseException, InterruptedException {

		Thread.sleep(2000);
		JsonMap jsonResponseMap = new JsonMap();
		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		long id = documentTypeService.addDocumentType(documentType);
		if (id > 0) {
			jsonResponseMap.setId(id);
			jsonResponseMap.setSuccessStatus();
			jsonResponseMap.setMessages("documentTypeSuccess");

		} else {
			jsonResponseMap.setMessages("documentTypeAddFail");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;

	}

	@RequestMapping(value = "/documentType", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<DocumentTypeJson> listAll(DocumentType documentType) throws ParseException {
		List<DocumentType> documentTypes = documentTypeService.listAll();
		List<DocumentTypeJson> jsonDocumentTypes = new ArrayList<>();
		for (DocumentType dt : documentTypes) {
			DocumentTypeJson documentTypeJson = new DocumentTypeJson();
			documentTypeJson.setDocumentTypeId(dt.getId());
			documentTypeJson.setDescription(dt.getDescription());

			jsonDocumentTypes.add(documentTypeJson);
		}
		return jsonDocumentTypes;
	}

	@RequestMapping(value = "/documentType/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public DocumentTypeJson getById(@PathVariable long id) throws ParseException {
		DocumentType dt = documentTypeService.getDocumentTypeById(id);
		DocumentTypeJson documentTypeJson = new DocumentTypeJson();
		documentTypeJson.setDocumentTypeId(dt.getId());
		documentTypeJson.setDescription(dt.getDescription());
		return documentTypeJson;

	}

	@RequestMapping(value = "/documentType/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap editById(@Valid @PathVariable long id, @Valid @RequestBody DocumentType documentType, BindingResult result) throws ParseException {

		DocumentType dt = documentTypeService.getDocumentTypeById(id);
		JsonMap jsonResponseMap = new JsonMap();
		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		if (dt == null) {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("documentTypeNotFound");
			return jsonResponseMap;
		}

		documentType.setId(id);
		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");

			return jsonResponseMap;
		}
		long typeId = documentTypeService.addDocumentType(documentType);
		if (typeId > 0) {
			jsonResponseMap.setMessages("documentTypeUpdateSuccess");
			jsonResponseMap.setSuccessStatus();
		} else {
			jsonResponseMap.setMessages("documentTypeUpdateFail");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;
	}

	@RequestMapping(value = "/documentType/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteDocumentTypeById(@PathVariable long id) throws ParseException {

		DocumentType dt = documentTypeService.getDocumentTypeById(id);
		JsonMap jsonResponseMap = new JsonMap();

		if (dt == null) {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("documentTypeNotFound");
			return jsonResponseMap;
		}
		long typeId = documentTypeService.deleteDocumentType(id);
		if (typeId > 0) {
			jsonResponseMap.setMessages("documentTypeDeleteSuccess");
			jsonResponseMap.setStatus("successStatus");
		} else {
			jsonResponseMap.setMessages("documentTypeDeleteFail");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;

	}
}