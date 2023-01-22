package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LocalAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer localAddressId;
	private String areaName;
	private String cityName;
	private String district;
	private String state;
	private Integer pincode;
	
	private String streetName;

}
