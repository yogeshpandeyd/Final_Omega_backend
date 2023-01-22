package com.myteam.carloanapp.app.entity;

import org.springframework.beans.factory.annotation.Value;

public class CustomerEmailSender {


	EmailDetails emailDetails= new EmailDetails();
	
	@Value("${spring.mail.username}")
	private String sendFrom;
	
	public String sendLoanFormMail() {
		
		emailDetails.setFromEmail(sendFrom);
		
		try {
			
		}
		catch (Exception e) {
		}
		
		return null;
	}
	
}
