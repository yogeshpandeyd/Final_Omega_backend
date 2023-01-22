package com.myteam.carloanapp.app.entity;

import java.util.concurrent.ThreadLocalRandom;

// This class generates cibil score by income

public class CibilScoreGenerator {

	public Integer genrateCibil(String pan,Integer income){
		Integer cibilScore=0;
		
		if(income>1000000 ) {
		
			return cibilScore=ThreadLocalRandom.current().nextInt(850,899+1);
				
		}else if(income >=600000 && income<1000000) {
			
			return cibilScore=ThreadLocalRandom.current().nextInt(700,749+1);
			
		}else if(income >300000 && income<600000){
	
			return cibilScore=ThreadLocalRandom.current().nextInt(650,699+1);
		}
		else if(income <300000){
			
			return cibilScore=ThreadLocalRandom.current().nextInt(580,649+1);
		}
		return cibilScore;
	
	}
	
}
