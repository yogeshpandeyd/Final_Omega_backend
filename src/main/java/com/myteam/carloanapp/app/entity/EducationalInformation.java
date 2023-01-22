package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// NOT USED IN APP
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class EducationalInformation {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer educationId;
	private String educationType;
}
