package com.myteam.carloanapp.app.servicei;

import java.util.List;
import java.util.Optional;

import com.myteam.carloanapp.app.entity.EmailDetails;
import com.myteam.carloanapp.app.entity.EnquiryDetails;

public interface EnquiryDetailsServiceI {
	 
	public EnquiryDetails saveenquirydetails(EnquiryDetails enquiryDetails);

	public Optional<EnquiryDetails> getsingleenquiry(Integer enquiryid);

	public List<EnquiryDetails> getAllEnquiry();

	public void emailsend(EmailDetails e);

	public List<EnquiryDetails> getEnquiredPersonByStatus(String Enquirystatus);
	
	//added for cibil check
	public EnquiryDetails getEnquiredByIdAndStatus(Integer id);

	public void deleteEnquiryDetail(Integer enquiryid);
	public void sendEmailToCibilRejected(Integer id,Integer cibilscore);
	public void sendEmailToCibilVerified(Integer id,Integer cibilscore);
	

}
