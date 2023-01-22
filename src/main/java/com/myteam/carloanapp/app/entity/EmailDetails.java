package com.myteam.carloanapp.app.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
	
	@Id
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String text;

}
