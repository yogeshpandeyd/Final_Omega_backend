package com.myteam.carloanapp.app.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.myteam.carloanapp.app.entity.Customer;
import com.myteam.carloanapp.app.entity.EnquiryDetails;
import com.myteam.carloanapp.app.entity.Ledger;

@Repository

public interface CustomerLoanFormRepository extends JpaRepository<Customer, Integer>{


	public Optional<Customer> findByCustomerIdAndStatus(Integer id, String status);

	public Optional<Customer> findByStatus(String status);
	
	public List<Customer> findAllByStatus(String status);
	
	
}
