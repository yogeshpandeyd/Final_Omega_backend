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
public class Ledger
{
	@Id
	
	private Integer srno;//added
	private Integer emiNo;//added
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer ledgerId;

	private Integer ledgerCreatedDate;
	private Integer ledgerCreatedMonth;
	private Integer ledgerCreatedYear;
	private Double totalLoanAmount;
    private Double payableAmountwithInterest;
	private Double tenure;
	private Double monthlyEmi;

//	private Double amountPaidtillDate;
//	private Double remainingAmount;
//	private String nextEmiDatestart;
//	private String nextEmiDateEnd;
//	private Integer defaulterCount;
//	private String previousEmitStatus;

	private Double totalEmiCount;
	private Integer defaulterCount;
	private String currentMonthEmiStatus;
	private String loanEndDate;
	private String loanStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;
}
