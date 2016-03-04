package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.service.FileUploadService;
import com.vastikaEIS.util.FileUploadUtil;
import com.vastikaEIS.util.MessageProperties;
import com.vastikaEIS.util.StringManipulation;

@RestController
@RequestMapping(value = "/api")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public String getStarted(ModelMap model) {
		return "fileUpload";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public JsonMap fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		JsonMap jsonMap = new JsonMap();
		FileUpload fileUpload = fileUploadUtil.uploadFile(file);
		if (fileUpload !=null) {
			jsonMap.setMessages("fileUploadSuccessMsg");
			jsonMap.setId(fileUpload.getId());
			String fileName = fileUpload.getRealFileName();
			String extension = StringManipulation.getFileExtension(fileName);
			jsonMap.setFileType(extension);
			jsonMap.setSuccessStatus();
			return jsonMap;
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setId(fileUpload.getId());
			return jsonMap;
		}
	}

	@RequestMapping(value = "/fileUpload/{fileUploadId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteFileUploadById(@PathVariable long fileUploadId) throws ParseException {
		FileUpload fileUpload = fileUploadService.findFileUploadById(fileUploadId);

		JsonMap jsonResponseMap = new JsonMap();
		MessageProperties msgProperty = new MessageProperties();
		Properties prop = msgProperty.getPropValues();

		if (fileUpload == null) {
			jsonResponseMap.put("status", prop.getProperty("failureStatus"));
			jsonResponseMap.put("message", prop.getProperty("fileNotFound"));
			return jsonResponseMap;
		}

		boolean success = fileUploadService.deleteFileUploadById(fileUploadId);
		if (success) {
			jsonResponseMap.put("message", prop.getProperty("fileDeleteSuccess"));
			jsonResponseMap.put("status", prop.getProperty("successStatus"));
		} else {
			jsonResponseMap.put("message", prop.getProperty("fileDeleteFail"));
			jsonResponseMap.put("status", prop.getProperty("failureStatus"));
		}

		return jsonResponseMap;
	}
}
