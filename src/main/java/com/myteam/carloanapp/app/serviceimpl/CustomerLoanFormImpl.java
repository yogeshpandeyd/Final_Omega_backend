package com.myteam.carloanapp.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myteam.carloanapp.app.entity.Customer;
import com.myteam.carloanapp.app.exception.CustomerNotPresentException;
import com.myteam.carloanapp.app.exception.CustomerNotSavedException;
import com.myteam.carloanapp.app.repository.CustomerLoanFormRepository;
import com.myteam.carloanapp.app.servicei.CustomerLoanFormI;

@Service
public class CustomerLoanFormImpl implements CustomerLoanFormI {

	@Autowired
	CustomerLoanFormRepository customerRepo;

	@Override
	public Customer saveCustomerDetails(Customer customer) {
		customer.setStatus("Loan_Form_Filled");
		
			return customerRepo.save(customer);
		
	}
	
	public Customer verifyCustomerDetails(Integer custoumerid) {
		Optional<Customer> findById = customerRepo.findById(custoumerid);
		findById.get().setStatus("Documents_Verified");
		
			return customerRepo.save(findById.get());
		
	}

	public Optional<Customer> getCustomerById(Integer id) {

		return customerRepo.findById(id);
	}


	@Override
	public Optional<Customer> findByCustomerIdAndCustomerLoanStatus(Integer id, String status) {

		try {
			Optional<Customer> findByCustomerIdAndStatus = customerRepo.findByCustomerIdAndStatus(id, status);
			return findByCustomerIdAndStatus;
		} catch (Exception e) {

			throw new CustomerNotPresentException("Customer With ID And Status Not Found..!!");
		}

	}

	@Override
	public Optional<Customer> findByCustomerLoanStatus(String status) {

		try {
			return customerRepo.findByStatus(status);
		} catch (Exception e) {

			throw new CustomerNotPresentException("Customer With this Status Not Present");
		}
	}
	
	public List<Customer> findALLByCustomerLoanStatus(String status) {

		try {
		List<Customer> findAllByStatus = customerRepo.findAllByStatus(status);
		return findAllByStatus;
		} catch (Exception e) {

			throw new CustomerNotPresentException("Customer With this Status Not Present");
		}
	}
	


}
