package com.myteam.carloanapp.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class CarInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carRegisterNo;
	private String carName;
	private String carColor;
	private Double carPrice;

	@OneToOne(cascade = CascadeType.ALL)
	private  ShowroomAddress showroomDetails;
}
