package com.myteam.carloanapp.app.servicei;

import java.util.List;
import java.util.Optional;

import com.myteam.carloanapp.app.entity.Customer;

public interface CustomerLoanFormI {

public	Customer saveCustomerDetails(Customer customer);

public Customer verifyCustomerDetails(Integer custoumerid);

//public void getallCustoumerbyledgerid(Integer srno);


	
public Optional<Customer> getCustomerById(Integer id);

public Optional<Customer> findByCustomerIdAndCustomerLoanStatus(Integer id, String status);

public Optional<Customer> findByCustomerLoanStatus(String status);

public List<Customer> findALLByCustomerLoanStatus(String status);
}
