package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDetails {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)//changed removed d auto generate
	private Integer customerId;
//	private String firstName;
	private String customerFirstName;
//	private String lastName;
	private String customerMiddleName;
	private String customerLastName;
//	private Integer age;
	private String customerLoanFormFillDate;
	private String customerEmail;
//	private Long mobileNo;
	private Long customerMobileNumber;
//	private String pancardNo;
	private String customerPanCard;
	private String status;
	
	//added for cibil check
	private Integer annualIncome;
	
	
	//cibil added for loan form
	private Integer customerCibilScore;
//	private Integer customerCibilScore;
	
}
