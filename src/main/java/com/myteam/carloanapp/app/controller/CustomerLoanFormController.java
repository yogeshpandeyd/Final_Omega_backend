package com.myteam.carloanapp.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.carloanapp.app.entity.BaseResponce;
import com.myteam.carloanapp.app.entity.Customer;
import com.myteam.carloanapp.app.servicei.CustomerLoanFormI;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/customer")
public class CustomerLoanFormController {
	@Autowired
	CustomerLoanFormI customerService;

	// http://localhost:9999/customer/saveCustomerDetails
	@PostMapping(value = "/saveCustomerDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponce<Customer>> saveCustomerDetails(
			@RequestPart(value = "panCard") MultipartFile panCard,
			@RequestPart(value = "adhaarCard") MultipartFile adhaarCard,
			@RequestPart(value = "incomeProof") MultipartFile incomeProof,
			@RequestPart(value = "photo") MultipartFile photo,
			@RequestPart(value = "signature") MultipartFile signature,
			@RequestPart(value = "bankPassBook") MultipartFile bankPassBook,
			@RequestPart(value = "allData") String allData) {

		BaseResponce<Customer> baseRespCustomer = null;
		ObjectMapper om = new ObjectMapper();
		try {

			Customer readCustomerValue = om.readValue(allData, Customer.class);

			readCustomerValue.getCustomerAllDocuments().setPanCard(panCard.getBytes());
			readCustomerValue.getCustomerAllDocuments().setAdhaarCard(adhaarCard.getBytes());
			readCustomerValue.getCustomerAllDocuments().setIncomeProof(incomeProof.getBytes());
			readCustomerValue.getCustomerAllDocuments().setPhoto(incomeProof.getBytes());
			readCustomerValue.getCustomerAllDocuments().setSignature(signature.getBytes());
			readCustomerValue.getCustomerAllDocuments().setBankPassBook(bankPassBook.getBytes());

			Customer savedCustomerDetails = customerService.saveCustomerDetails(readCustomerValue);
			baseRespCustomer = new BaseResponce<Customer>(201, "Customer Details Saved Successfully..!!",
					savedCustomerDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<BaseResponce<Customer>>(baseRespCustomer, HttpStatus.OK);
	}

	// http://localhost:9999/customer/verifyCustomerDetails
	@GetMapping(value = "/verifyCustomerDetails/{custoumerid}")
	public ResponseEntity<BaseResponce<Customer>> verifyCustomerDetails(

			@PathVariable("custoumerid") Integer custoumerid) {

		BaseResponce<Customer> baseRespCustomer = null;

		Customer verifiedCustomerDetails = customerService.verifyCustomerDetails(custoumerid);
		baseRespCustomer = new BaseResponce<Customer>(201, "Customer Details Verified Successfully..!!",
				verifiedCustomerDetails);

		return new ResponseEntity<BaseResponce<Customer>>(baseRespCustomer, HttpStatus.OK);
	}

	// http://localhost:9999/customer/getCustomerById/id
	@GetMapping(value = "/getCustomerById/{id}")
	public ResponseEntity<BaseResponce<Customer>> getCustomerById(@PathVariable("id") Integer id) {
		Optional<Customer> customerById = customerService.getCustomerById(id);
		Customer customer = customerById.get();
		BaseResponce<Customer> base = new BaseResponce<Customer>(200, "Customer By Id", customer);

		return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);
	}

	// http://localhost:9999/customer/getCustomerByIdAndStatus/id/status
	@GetMapping(value = "/getCustomerByIdAndStatus/{id}/{status}")
	public ResponseEntity<BaseResponce<Customer>> getCustomerByIdAndStatus(@PathVariable("id") Integer id,
			@PathVariable String status) {
		Optional<Customer> customerByIdAndStatus = customerService.findByCustomerIdAndCustomerLoanStatus(id, status);
		Customer customer = customerByIdAndStatus.get();
		BaseResponce<Customer> base = new BaseResponce<Customer>(200, "geting by id and status", customer);
		return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);
	}

	// http://localhost:9999/customer/getCustomerByIdAndStatus/status
	@GetMapping(value = "/getCustomerByIdAndStatus/{status}")
	public ResponseEntity<BaseResponce<Customer>> getCustomerByStatus(@PathVariable String status) {
		Optional<Customer> customerByStatus = customerService.findByCustomerLoanStatus(status);
		Customer customer = customerByStatus.get();
		BaseResponce<Customer> base = new BaseResponce<Customer>(200, "geting by id and status", customer);
		return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);
	}

	@GetMapping("/getAllCustomerByStatus/{status}")
	public ResponseEntity<BaseResponce< List<Customer>>> getCustomerAllByStatus(@PathVariable String status) {
		 List<Customer> findALLByCustomerLoanStatus = customerService.findALLByCustomerLoanStatus(status);
		
		BaseResponce< List<Customer>> base = new BaseResponce< List<Customer>>(200, "geting by id and status", findALLByCustomerLoanStatus);
		return new ResponseEntity<BaseResponce< List<Customer>>>(base, HttpStatus.OK);
	}

	
}
