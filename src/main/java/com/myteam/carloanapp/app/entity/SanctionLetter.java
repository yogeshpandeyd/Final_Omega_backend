package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class SanctionLetter 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sanctionId;
	private String sanctionDate;
	
	private String applicantName;
	private Long contactDetails;
  //private String producthomeEquity; apala vechile loan ahe 
	private Double loanAmtSanctioned;
	private String interestType;
	private Double rateOfInterest;
	private Double loanTenure;
	private Double monthlyEmiAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsCondition;
	private String status;

}
