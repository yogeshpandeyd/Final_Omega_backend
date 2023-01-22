package com.myteam.carloanapp.app.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myteam.carloanapp.app.entity.BaseResponce;
import com.myteam.carloanapp.app.entity.CibilScoreGenerator;
import com.myteam.carloanapp.app.entity.EmailDetails;
import com.myteam.carloanapp.app.entity.EnquiryDetails;
import com.myteam.carloanapp.app.enums.EnquiryStatus;
import com.myteam.carloanapp.app.exception.userNotPresentException;
import com.myteam.carloanapp.app.servicei.EnquiryDetailsServiceI;

@CrossOrigin("*")
@RestController
@RequestMapping("/enquiry")
public class EnquiryController {
	
	
	
	private String emailOfEnquired;
	
	
	
	EmailDetails e=new EmailDetails();
	 
	 @Value("${spring.mail.username}")
	private String reMailid;
	 
	 @Autowired
	EnquiryDetailsServiceI enquiryDetailsServicei;
	
	
	
	@PostMapping("/saveenquirydetails")
	public ResponseEntity<BaseResponce<EnquiryDetails>> saveenquirydetails(@RequestBody EnquiryDetails enquiryDetails)
	{
	emailOfEnquired=enquiryDetails.getCustomerEmail();
	enquiryDetails.setStatus("Enquired");
	EnquiryDetails saveenquirydetails = enquiryDetailsServicei.saveenquirydetails(enquiryDetails);
	
	sendemaileqiryperson();
	BaseResponce<EnquiryDetails> baseResponceenquirydetails=new BaseResponce<EnquiryDetails>(201,"Enquired save succesfully", saveenquirydetails);
	
	
	return new	ResponseEntity<BaseResponce<EnquiryDetails>>(baseResponceenquirydetails,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getsingleenquirydetails/{id}")
	public ResponseEntity<BaseResponce<Optional<EnquiryDetails>>> getsingleenquiry(@PathVariable ("id") Integer enquiryid)
	{
		
		Optional<EnquiryDetails> getsingleenquiry = enquiryDetailsServicei.getsingleenquiry(enquiryid);
		if(getsingleenquiry.isEmpty())
		{
			throw new userNotPresentException("Please Enter Valid ID");
		}
		BaseResponce<Optional<EnquiryDetails>> baseResponcesingle =new BaseResponce<Optional<EnquiryDetails>>(200,"Show Single Enquirey", getsingleenquiry);
		return new ResponseEntity<BaseResponce<Optional<EnquiryDetails>>>(baseResponcesingle, HttpStatus.OK);
	}
	
	@GetMapping("/getallenquiry")
	public ResponseEntity<BaseResponce<List<EnquiryDetails>>> getAllEnquiry()
	{
		List<EnquiryDetails> allEnquiry = enquiryDetailsServicei.getAllEnquiry();
		
		BaseResponce<List<EnquiryDetails>> baseresponcealldata=new BaseResponce<List<EnquiryDetails>>(200,"Display All Enquiry" , allEnquiry);
		System.out.println(allEnquiry);
	return new	ResponseEntity<BaseResponce<List<EnquiryDetails>>>(baseresponcealldata, HttpStatus.OK);
		
	}
	
	
	public String sendemaileqiryperson()
	{ 
		e.setFromEmail(reMailid);
		try {
			e.setToEmail(emailOfEnquired);
			e.setText("Enquired done Succesfully On Enquire on Omega Finance App");
			e.setSubject("Enquire on Omega Finance App");
			
			System.out.println(reMailid);
			enquiryDetailsServicei.emailsend(e);
			emailOfEnquired=null;
			return "Email Send Succesfully";
		} catch (Exception e2) {

			return "email not send";
		}
		
	}
	
	@GetMapping("/getenquiredpersonbystatus/{status}")
	public ResponseEntity<BaseResponce<List<EnquiryDetails>>> getEnquiredPersonByStatus(@ PathVariable ("status") String Enquirystatus)
	{
		List<EnquiryDetails> enquiredPersonByStatus = enquiryDetailsServicei.getEnquiredPersonByStatus(Enquirystatus);
		BaseResponce<List<EnquiryDetails>> baseResponceOfenquiredPersonByStatus=new BaseResponce<List<EnquiryDetails>>(201, "All Data enquiredPersonByStatus", enquiredPersonByStatus);
		return new ResponseEntity<BaseResponce<List<EnquiryDetails>>>(baseResponceOfenquiredPersonByStatus,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteenquiry/{id}")
	public ResponseEntity<BaseResponce> deleteEnquiryDetail(@PathVariable ("id") Integer enquiryid)
	{
		enquiryDetailsServicei.deleteEnquiryDetail(enquiryid);
		BaseResponce deletebaseresponce=new BaseResponce (200,"Delete Enquiry Succesfully",null);
		return new ResponseEntity<BaseResponce>(deletebaseresponce, HttpStatus.OK);
	}
	
	//added for cibil check


	

	@GetMapping("/checkcibilscore/{id}")
	public Integer checkCibilScore(@PathVariable("id") Integer enquiryId) {


		CibilScoreGenerator generateCibil= new CibilScoreGenerator();

		EnquiryDetails enquiredByIdAndStatus = enquiryDetailsServicei.getEnquiredByIdAndStatus(enquiryId);
		
		String pancardNo = enquiredByIdAndStatus.getCustomerPanCard(); //just for sending details of panCard
		Integer annualIncome = enquiredByIdAndStatus.getAnnualIncome();//checked by income
		CibilScoreGenerator cibilGenerator=new CibilScoreGenerator();
		Integer genrateCibil = cibilGenerator.genrateCibil(pancardNo, annualIncome);
		if(genrateCibil>750)
		{
			enquiryDetailsServicei.sendEmailToCibilVerified(enquiryId, genrateCibil);
			Optional<EnquiryDetails> getsingleenquiry = enquiryDetailsServicei.getsingleenquiry(enquiryId);
			 Integer cibilAccepted=genrateCibil;
			 
			getsingleenquiry.get().setStatus("Cibil Approved");
			EnquiryDetails enquiry1 = getsingleenquiry.get();
			enquiry1.setCustomerCibilScore(cibilAccepted);
			System.err.println(enquiry1.getCustomerCibilScore());
			enquiryDetailsServicei.saveenquirydetails(enquiry1);
			
			return 1;
			
		}
		 else 
		{
			enquiryDetailsServicei.sendEmailToCibilRejected(enquiryId, genrateCibil);
			Optional<EnquiryDetails> getsingleenquiry = enquiryDetailsServicei.getsingleenquiry(enquiryId);
			Integer cibilRejected=genrateCibil;
			getsingleenquiry.get().setStatus("Cibil Rejected");
			EnquiryDetails enquiry1 = getsingleenquiry.get();
			enquiry1.setCustomerCibilScore(genrateCibil);
			enquiryDetailsServicei.saveenquirydetails(enquiry1);
			
			
			return 2;
			
			
		}
		
	}

	
	
}
