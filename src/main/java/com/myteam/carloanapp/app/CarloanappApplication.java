package com.myteam.carloanapp.app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import javax.mail.Address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.carloanapp.app.entity.SanctionLetter;

@SpringBootApplication()
public class CarloanappApplication {

	public static void main(String[] args) {

		System.out.println("Ganapati Bappa Morya............!");
		ObjectMapper mapper = new ObjectMapper();
		try {

			System.out.println(mapper.writeValueAsString(new SanctionLetter()));

		} catch (Exception e) {

		}
		System.out.println("rohit");
		SpringApplication.run(CarloanappApplication.class, args);
		System.out.println("by shubham");
		System.out.println("girish");
		System.out.println("by yogesh");
		System.out.println("Dhanshri");
		
//		Double p=1000000d;
//		Double r=0.006d;
//		Double n=60d;
//		
//		
//		
//		
//		Double emi= p*r*(Math.pow((1+r), n))/((Math.pow((1+r), n))-1);
//		System.out.println(emi);
		
//		String s="12-10-2022";
//		String[] split = s.split("-");
//	
//	       String date = split[0];
//		    String month = split[1];
//		    String year = split[2];
//		    Integer date1=(Integer)new Integer(date);
//		    Integer month1=(Integer)new Integer(month);
//		    Integer year1=(Integer)new Integer(year);
//		    System.out.println(date1);
//		    System.out.println(month1);
//		    System.out.println(year1);
		    

	}

}
