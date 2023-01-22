package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DealerBankDetails {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dealerId;
	private String dealerName;
	private String dealerAddress;
	private Integer dealerBankAccountNumber;
	private String dealerBankName;
	private String dealerBankBranchName;
	private String dealerBankIfscNumber;


}
