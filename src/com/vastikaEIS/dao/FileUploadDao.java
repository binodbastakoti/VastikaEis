package com.vastikaEIS.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.FileUpload;

@Repository
public class FileUploadDao {

	@Autowired
	SessionFactory sf;

	public FileUpload findFileUploadById(long id) {
		try {
			FileUpload fileUpload = (FileUpload) sf.getCurrentSession().get(FileUpload.class, id);
			return fileUpload;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteFileUploadById(FileUpload fileUpload) {
		try {
			
			sf.getCurrentSession().delete(fileUpload);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
