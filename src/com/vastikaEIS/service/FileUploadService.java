package com.vastikaEIS.service;

import com.vastikaEIS.domain.FileUpload;

public interface FileUploadService {

	public FileUpload findFileUploadById(long fileUploadId);

	public boolean deleteFileUploadById(long fileUploadId); 
}
