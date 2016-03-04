package com.vastikaEIS.util;

import java.util.List;

import com.vastikaEIS.constant.Constant;

public class StringManipulation {

	public static String manipulatedString(List<String> inputString, String separator) {
		String returnString = inputString.get(0);
		for (int i = 1; i < inputString.size(); i++) {
			if (inputString.get(i) != "" || inputString.get(i) != null) {
				returnString = returnString + separator + inputString.get(i);
			}
		}
		return returnString;

	}

	public static String getFileExtension(String documentName) {
		try {
			int indexOfDot = documentName.lastIndexOf('.');
			String documentExtension = documentName.substring(indexOfDot + 1);
			if (documentExtension.equalsIgnoreCase(Constant.GIF) || documentExtension.equalsIgnoreCase(Constant.JPEG)
					|| documentExtension.equalsIgnoreCase(Constant.JPG) || documentExtension.equalsIgnoreCase(Constant.PNG)) {
				return "img";
			} else {
				return "doc";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
