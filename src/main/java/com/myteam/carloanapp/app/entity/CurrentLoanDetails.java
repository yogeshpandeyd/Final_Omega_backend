package com.myteam.carloanapp.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CurrentLoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer currentloanId;
	private Integer currentloanNo;
	@OneToOne(cascade = CascadeType.ALL)
	private EmiDetails emiDetails;
	private Double loanAmount;
	private Integer rateOfInterest;
	private Integer tenure;
	private Double totalAmounttobePaidDouble;
	private Integer processingFees;
	private Double totalInterest;
	private String sanctionDate;
	private String remark;
	private String status;
}
