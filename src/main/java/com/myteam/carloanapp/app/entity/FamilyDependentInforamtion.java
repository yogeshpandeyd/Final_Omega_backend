package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FamilyDependentInforamtion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dependentInfoId;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String maritalStatus;
	private String dependentMember;
	private Double familyIncome;
	
	
	
	
	

}
