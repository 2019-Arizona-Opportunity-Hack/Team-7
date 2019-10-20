package com.zuri.circle.manager.services;

import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.zuri.circle.manager.models.User;

@Service
public class EmailService {
	
	

	@Autowired
	private MailBuilder mailBuilder;

	
	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;

	}

	public String sendMail(User user, String url) throws Exception {
		MimeMessagePreparator message = messageSender -> {
			MimeMessageHelper helper = new MimeMessageHelper(messageSender);
			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			String html = "A fundraiser event is organized at Galvanize.\n Confirm your availability by clicking on the link below.\n"  + "<a href="+url+">EventConfirmation</a>";
			MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
			helper.setTo(user.getEmail());
			helper.setFrom("kmrprabhu93@gmail.com");
			helper.setSubject("Registration Success");
			// message.setText(content, true);
			String content = mailBuilder.build(user.getFirstName(), html);
			helper.setText(content, true);
			
		};

		try {
			mailSender.send(message);
			return null;
		} catch (MailException m) {
			throw new Exception(m.getMessage());
		}

	}


}
