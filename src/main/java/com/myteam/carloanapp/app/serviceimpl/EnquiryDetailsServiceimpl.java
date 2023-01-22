package com.myteam.carloanapp.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.myteam.carloanapp.app.entity.CibilScoreGenerator;
import com.myteam.carloanapp.app.entity.EmailDetails;
import com.myteam.carloanapp.app.entity.EnquiryDetails;
import com.myteam.carloanapp.app.repository.EnquiryDetailsRepository;
import com.myteam.carloanapp.app.servicei.EnquiryDetailsServiceI;

@Service
public class EnquiryDetailsServiceimpl implements EnquiryDetailsServiceI {

	private String emailOfEnquired;
	
	EmailDetails e=new EmailDetails();
	 
	 @Value("${spring.mail.username}")
	private String reMailid;
	
	@Autowired
	EnquiryDetailsRepository enquiryDetailsrepository;
	
	@Autowired
	JavaMailSender jms;

	@Override
	public EnquiryDetails saveenquirydetails(EnquiryDetails enquiryDetails) {
		EnquiryDetails saveenquiry = enquiryDetailsrepository.save(enquiryDetails);
		return saveenquiry;
	}

	@Override
	public Optional<EnquiryDetails> getsingleenquiry(Integer enquiryid) {
		Optional<EnquiryDetails> singleenquiry = enquiryDetailsrepository.findById(enquiryid);
		return singleenquiry;
		
	}

	@Override
	public List<EnquiryDetails> getAllEnquiry() {
		List<EnquiryDetails> getallenquirys = enquiryDetailsrepository.findAll();
		return getallenquirys;
		
		
	}

	@Override
	public void emailsend(EmailDetails e) {
		
		SimpleMailMessage m=new SimpleMailMessage();
		m.setTo(e.getToEmail());
		m.setFrom(e.getFromEmail());
		m.setText(e.getText());
		m.setSubject(e.getSubject());
		jms.send(m);
		
		
	}

	@Override
	public List<EnquiryDetails> getEnquiredPersonByStatus(String Enquirystatus) {

		List<EnquiryDetails> findAllByEnquiryStatus = enquiryDetailsrepository.findAllByStatus(Enquirystatus);
		return findAllByEnquiryStatus;
		
	}

	@Override
	public void deleteEnquiryDetail(Integer enquiryid) {
		
		enquiryDetailsrepository.deleteById(enquiryid);
		
	}

	//added for cibil check
	
	@Override
	
	public EnquiryDetails getEnquiredByIdAndStatus(Integer id) {

		EnquiryDetails enquiredByIdAndStatus = enquiryDetailsrepository.findByCustomerId(id);
		
		
		return enquiredByIdAndStatus;
	}

	@Override
	public void sendEmailToCibilRejected(Integer Enuiryid,Integer cibilscore) {
	
		Optional<EnquiryDetails> enquiredinfo = enquiryDetailsrepository.findById(Enuiryid);
		emailOfEnquired=enquiredinfo.get().getCustomerEmail();
		SimpleMailMessage m=new SimpleMailMessage();
		m.setTo(emailOfEnquired);
		m.setFrom(reMailid);
		m.setText("Your CIBIL Score is"+cibilscore+"therefore We Can not provid loan Thaks For Enquiry Have A Great Day.....!!!");
		m.setSubject("Regarding CIBIL Score Rejected");
		jms.send(m);
		
	}

	@Override
	public void sendEmailToCibilVerified(Integer Enuiryid,Integer cibilscore) {
	
		Optional<EnquiryDetails> enquiredinfo = enquiryDetailsrepository.findById(Enuiryid);
		emailOfEnquired=enquiredinfo.get().getCustomerEmail();
		SimpleMailMessage m=new SimpleMailMessage();
		m.setTo(emailOfEnquired);
		m.setFrom(reMailid);
		m.setText("Your CIBIL Score is"+cibilscore+"Youu Are Eligible To Fill Loan Applicatin Form  Have A Great Day.....!!!");
		m.setSubject("Regarding CIBIL Score Approved");
		jms.send(m);
		
	}
	
	
	

}
