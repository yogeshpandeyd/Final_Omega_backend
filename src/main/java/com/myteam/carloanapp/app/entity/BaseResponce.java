package com.myteam.carloanapp.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BaseResponce<T> {
	
	private Integer statusCode;
	private String msg;
	private T responceData;

}
