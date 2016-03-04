package com.vastikaEIS.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.vastikaEIS.util.Datas;

@Service("mailService")
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * This method will send compose and send the message
	 *
	 */
	public void sendEmailRequest(final String to, final String subject, final String body) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				System.out.println("to-----" + to);
				message.setTo(to);
				// message.setFrom(from);
				message.setSubject(subject);
				message.setText(body);
				// Map model = new HashMap();
				// model.put("from", from);
				// model.put("message", body);
				// String text = body
				message.setText(Datas.html.replace("{link}", body), true);
			}
		};
		mailSender.send(preparator);
	}

	// public void sendMail(final String from, final String to, final String
	// subject, final String body) {
	// MimeMessagePreparator preparator = new MimeMessagePreparator() {
	// public void prepare(MimeMessage mimeMessage) throws Exception {
	// MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	// message.setTo(to);
	// message.setFrom(from);
	// message.setSubject(subject);
	// message.setText(body);
	// Map model = new HashMap();
	// model.put("from", from);
	// model.put("message", body);
	// String text = VelocityEngineUtils.mergeTemplateIntoString(
	// velocityEngine, "friend-request-confirmation.vm", model);
	// message.setText(text, true);
	// }
	// };
	// mailSender.send(preparator);
	// }

	public void sendMail(final String to, final String subject, final String body) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				// message.setFrom(from);
				message.setSubject(subject);
				message.setText(body);
				Map model = new HashMap();
				// model.put("from", from);
				model.put("message", body);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "userCredentials.vm", model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}

	/**
	 * This method will send a pre-configured message
	 *
	 */
	// public void sendPreConfiguredMail(String message) {
	// SimpleMailMessage mailMessage = new
	// SimpleMailMessage(preConfiguredMessage);
	// mailMessage.setText(message);
	// mailSender.send(mailMessage);
	// }
}
