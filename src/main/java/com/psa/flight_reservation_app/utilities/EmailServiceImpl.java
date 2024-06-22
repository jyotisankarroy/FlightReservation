package com.psa.flight_reservation_app.utilities;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String to, String subject, String message) {
		 SimpleMailMessage mailMessage = new SimpleMailMessage();
		 mailMessage.setTo(to);
		 mailMessage.setSubject(subject);
		 mailMessage.setText(message);
		 
		 mailSender.send(mailMessage);
	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String message, String filePath) {
		MimeMessage mimeMessage= mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		 try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message);
			
			//Adding the attachment
			FileSystemResource file= new FileSystemResource(new File(filePath));
			
			mimeMessageHelper.addAttachment("Itinerary", new File(filePath));
			
			mailSender.send(mimeMessage);
		 } catch (MessagingException e) {
			
			e.printStackTrace();
		}
	}

}
