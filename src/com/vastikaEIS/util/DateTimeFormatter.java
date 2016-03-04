package com.vastikaEIS.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

	public static String formatDateToString(Date date) {
		try {
			String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
			return formattedDate;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	// public static void main(String[] args) {
	// DateTimeFormatter.formatDateToString(new Date());
	// }
}
