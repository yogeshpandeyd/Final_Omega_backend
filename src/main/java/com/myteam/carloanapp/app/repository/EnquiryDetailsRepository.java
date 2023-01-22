package com.myteam.carloanapp.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myteam.carloanapp.app.entity.EnquiryDetails;

@Repository
public interface EnquiryDetailsRepository extends JpaRepository<EnquiryDetails, Integer> 
{

	
	 public List<EnquiryDetails> findAllByStatus(String status);

		//added for cibil check
	 public EnquiryDetails findByCustomerId(Integer id);
	 
	 
}
