package com.vastikaEIS.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class MessageProperties {
	public Properties getPropValues() {
		InputStream inputStream;

		try {
			Properties prop = new Properties();
			String propFileName = "messages.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			return prop;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return null;
		}
	}
//
//	public Map<String, Object> setMessages(String message) {
//		Map<String, Object> jsonResponseMap = new HashMap<String, Object>();
//		Properties prop = getPropValues();
//		jsonResponseMap.put(Constant.MESSAGE, prop.getProperty(message));
//		return jsonResponseMap;
//	}
//
//	public Map<String, Object> setStatus(String message) {
//		Map<String, Object> jsonResponseMap = new HashMap<String, Object>();
//		Properties prop = getPropValues();
//		jsonResponseMap.put(Constant.STATUS, prop.getProperty(message));
//		return jsonResponseMap;
//	}

}
