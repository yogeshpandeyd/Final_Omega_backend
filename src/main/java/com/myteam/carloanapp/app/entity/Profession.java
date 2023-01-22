package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer professionId;
	private String professionType;
	private Double professionMonthlyIncome;
	private String professionDesignation;
	private Integer professionWorkingPeriod;

}
