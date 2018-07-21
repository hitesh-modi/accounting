package com.prompt.marginplus.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.prompt.marginplus.models.EmailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("emailUtility")
public class EmailUtility {
	
	
	private static final Logger log = LoggerFactory.getLogger(EmailUtility.class);

	
	@Value("$mail.smtp.auth")
	private String smtpAuth;
	
	@Value("mail.smtp.starttls.enable")
	private String smtpStartTLSEnable;
	
	@Value("mail.smtp.host")
	private String smtpHost;
	
	@Value("mail.smtp.port")
	private String smtpPort;

	public static void main(String[] args) {
		EmailUtility util = new EmailUtility();
		util.sendEmail(null);
	}
	
	public void sendEmail(EmailModel emailModel) {
		
		log.debug("Creating smtp connection with details : mail.smtp.auth :" + smtpAuth 
				+ ", mail.smtp.starttls.enable : "+smtpStartTLSEnable+ ", mail.smtp.host : " + smtpHost + ", mail.smtp.port :" + smtpPort);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("er.hiteshmodi@gmail.com", "0mNamahShivay123!");
					}
				  });
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("hitesh.modi@mail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	}
	

