package com.will.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Value("${spring.mail.username}")
	private String fromEmailAddress;
	
	@Autowired
    private JavaMailSender mailSender;
	
	public boolean send(String email, String mensagem) {
		
		SimpleMailMessage message =  new SimpleMailMessage();
		message.setTo(email);
		message.setFrom(fromEmailAddress);
		message.setSubject("Fechamento");
		message.setText(mensagem);
		
		mailSender.send(message);
		return true;
	}
	
}

