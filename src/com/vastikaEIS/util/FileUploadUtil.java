package com.vastikaEIS.util;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vastikaEIS.domain.FileUpload;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.DocumentsService;

@Service
public class FileUploadUtil {
	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private SecurityUtil securityUtil;

	public FileUpload uploadFile(MultipartFile file) throws Exception {
		String originalFileName = file.getOriginalFilename();
		ApplicationProperties applicationProperties = new ApplicationProperties();
		if (file.getSize() > 0) {
			String uniqueFileName = getRandomString() + System.nanoTime();

			String destinationPath = applicationProperties.getPropValues();
			File destinationFolder = new File(destinationPath);
			// check folder is exists or not
			if (!destinationFolder.exists()) {
				destinationFolder.mkdirs();
			}

			try {
				String destinationFilePath = destinationPath + File.separator + uniqueFileName;

				file.transferTo(new File(destinationFilePath));
				// perform database operation
				User uploadedBy = securityUtil.getSessionUser();
				FileUpload fileUpload = new FileUpload();
				fileUpload.setUploadedBy(uploadedBy);
				fileUpload.setRealFileName(originalFileName);
				fileUpload.setEncryptedFileName(uniqueFileName);
				fileUpload.setFilePath(destinationFilePath);
				fileUpload.setUploadedDate(new Date());
				FileUpload fileUploadId = documentsService.saveDocuments(fileUpload);
				return fileUploadId;
			} catch (Exception errorAttachingFile) {
				System.out.println(errorAttachingFile);
				throw (errorAttachingFile);
			}
		}
		return null;
	}

	/*
	 * The method is for generating 10 characters random number. The method is
	 * used in making the uploaded file name unique.
	 */
	public String getRandomString() throws Exception {
		return RandomStringGenerator.generateRandomString(10, RandomStringGenerator.Mode.ALPHANUMERIC);
	}
}
