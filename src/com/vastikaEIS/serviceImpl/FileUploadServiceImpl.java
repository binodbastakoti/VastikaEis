package com.vastikaEIS.serviceImpl;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.FileUploadDao;
import com.vastikaEIS.dao.ResumeDao;
import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.service.FileUploadService;
import com.vastikaEIS.util.ApplicationProperties;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	FileUploadDao fileUploadDao;

	@Autowired
	ResumeDao resumeDao;

	@Override
	public FileUpload findFileUploadById(long fileUploadId) {

		return fileUploadDao.findFileUploadById(fileUploadId);
	}

	@Override
	public boolean deleteFileUploadById(long fileUploadId) {
		FileUpload fileUpload = fileUploadDao.findFileUploadById(fileUploadId);

		// delete file from folder
		ApplicationProperties applicationProperties = new ApplicationProperties();
		String destinationPath = applicationProperties.getPropValues() + File.separator + fileUpload.getEncryptedFileName();
		File file = new File(destinationPath);

		if (file.delete()) {
			return fileUploadDao.deleteFileUploadById(fileUpload);
		} else {
			return false;
		}

	}

}
