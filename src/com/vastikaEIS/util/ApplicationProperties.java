package com.vastikaEIS.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	// public static void main(String[] args) {
	// ApplicationProperties applicationProperties = new
	// ApplicationProperties();
	// String filePath = applicationProperties.getPropValues();
	// System.out.println("filePath=====" + filePath);
	// }

	public String getPropValues() {
		String filePath = "";
		InputStream inputStream;

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			filePath = prop.getProperty("fileUploadPath");
			return filePath;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return filePath;
	}
}
