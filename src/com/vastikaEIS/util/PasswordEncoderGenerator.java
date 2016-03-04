package com.vastikaEIS.util;

/**
 *
 * @author 984351
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	public static String beCryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public static void main(String[] args) {
		System.out.println(beCryptPassword("admin"));
		// $2a$10$36l6Z0R9c12IchyNvXlDfuB6lQY4i70Aeua0JrW68CwoWaJJv8hbG

		// Calendar calendar = Calendar.getInstance();
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		// int month = calendar.get(Calendar.MONTH) + 1;
		// System.out.println("day==" + day);
		// System.out.println("month==" + month);
		// ---------------------------
		// String format1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		// String[] datearr = format1.split("-");
		// String day = datearr[0];
		// String month = datearr[1];
	}
}
