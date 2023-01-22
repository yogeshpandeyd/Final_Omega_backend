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
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerBankDetailsId;
	private Long customerBankAccountNumber;
	private String customerBankName;
	private String customerBankBranchName;
	private String customerBankIfscNumber;
	
}
