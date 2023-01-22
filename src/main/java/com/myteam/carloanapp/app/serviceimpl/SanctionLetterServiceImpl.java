package com.myteam.carloanapp.app.serviceimpl;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.myteam.carloanapp.app.entity.Customer;
import com.myteam.carloanapp.app.entity.EmailDetails;
import com.myteam.carloanapp.app.entity.SanctionLetter;
import com.myteam.carloanapp.app.repository.CustomerLoanFormRepository;
import com.myteam.carloanapp.app.repository.SanctionLetterRepository;
import com.myteam.carloanapp.app.servicei.SanctionLetterServiceI;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterServiceI {
	@Autowired
	SanctionLetterRepository sanctionLetterRepository;

	@Autowired
	CustomerLoanFormRepository customerLoanFormRepository;
	@Autowired
	JavaMailSender mailSender;

	EmailDetails e = new EmailDetails();
	SanctionLetter sanctionLetter = new SanctionLetter();

	@Value("${spring.mail.username}")
	private String reMailid;

	private Logger logger = LoggerFactory.getLogger(SanctionLetterServiceImpl.class);

	@Override
	public SanctionLetter generateSanctionLetter(@RequestBody SanctionLetter sanctionLetter) {
		SanctionLetter sanctionLetter2 = sanctionLetterRepository.save(sanctionLetter);
		return sanctionLetter2;
	}

	@Override
	public Optional<SanctionLetter> getSanctionLetterBySanctionId(Integer sanctionId) {
		Optional<SanctionLetter> findById = sanctionLetterRepository.findById(sanctionId);
		return findById;

	}

	@Override
	public List<SanctionLetter> getListOfAllSanctionLetter() {
		List<SanctionLetter> findAll = sanctionLetterRepository.findAll();
		return findAll;
	}

	@Override
	public void deleteSanctionLetterBySanctionId(Integer sanctionId) {
		sanctionLetterRepository.deleteById(sanctionId);

	}

	@Override
	public SanctionLetter saveSanctionLetterAfterUpdate(SanctionLetter sanctionLetter) {
		SanctionLetter sanctionLetter2 = sanctionLetterRepository.save(sanctionLetter);
		return sanctionLetter2;
	}

	@Override
	public SanctionLetter generateSanctionLetterAndSendByMailgirish(SanctionLetter sanctionLetter, Integer customerId) {
		Optional<Customer> findById = customerLoanFormRepository.findById(customerId);
		Customer customer = findById.get();

		// sanctionLetter.setSanctionId(customer.getSanctionLetter().getSanctionId());
		sanctionLetter.setApplicantName(customer.getCustomerFirstName()); // added changes
		sanctionLetter.setApplicantName(customer.getCustomerMiddleName()); // added changes
		sanctionLetter.setApplicantName(customer.getCustomerLastName()); // added changes

		sanctionLetter.setStatus("Approved");
		sanctionLetter.setLoanAmtSanctioned(sanctionLetter.getLoanAmtSanctioned());
		sanctionLetter.setLoanTenure(sanctionLetter.getLoanTenure());
		sanctionLetter.setRateOfInterest(sanctionLetter.getRateOfInterest());

//		For Converting Date To String Data Type
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDateTime = dateFormatter.format(new Date());

		sanctionLetter.setSanctionDate(currentDateTime);
		SanctionLetter sanctionLetter2 = sanctionLetterRepository.save(sanctionLetter);
		customer.setSanctionLetter(sanctionLetter2);
		customerLoanFormRepository.save(customer);

		logger.info("Create Pdf Started");

		String title = "OMEGA FINANCE SANCTION LETTER";
		String content = "Welcome to OMEGA FINANCE Mr." + customer.getSanctionLetter().getApplicantName()
				+ " You fullfill our all term and condition for your loan ."
				+ "that why we are generating your sanction letter with sanction Id "
				+ customer.getSanctionLetter().getSanctionId() + " And your sanctioned loan ammount is "
				+ customer.getSanctionLetter().getLoanAmtSanctioned() + " with rate of intrest "
				+ customer.getSanctionLetter().getRateOfInterest() + " for tenure of "
				+ customer.getSanctionLetter().getLoanTenure();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();

		PdfWriter.getInstance(document, out);

		document.open();

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26);
		titleFont.setColor(Color.magenta);

		Paragraph titlepara = new Paragraph(title, titleFont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);

		Font parafont = FontFactory.getFont(FontFactory.HELVETICA, 18);
		Paragraph paragraph = new Paragraph(content);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		document.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());

		MimeMessage createMimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage, true);
			mimeMessageHelper.setFrom(reMailid);
			System.out.println(reMailid);
			mimeMessageHelper.setTo(customer.getCustomerEmail());
			System.out.println(customer.getCustomerEmail());
			mimeMessageHelper.setSubject("OMEGA Car Loan");
			mimeMessageHelper.setText("Sanction Letter");

			byte[] readAllBytes = byteArrayInputStream.readAllBytes();

			mimeMessageHelper.addAttachment("sanctionletter.pdf", new ByteArrayResource(readAllBytes));
			mailSender.send(createMimeMessage);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sanctionLetter2;
	}
//not To use
	public SanctionLetter scantionAmmountToCustoumer(Integer custumerId) {
		Optional<Customer> custoumer = customerLoanFormRepository.findById(custumerId);

		Double income = custoumer.get().getAnnualIncome();
		Double customerLoanTeanre = custoumer.get().getCustomerLoanTeanre();
		Double n = customerLoanTeanre * 12;
		Double intrest = 7d;
		// Float intrest = sanctionLetter.getRateOfInterest();
		Double r = intrest / 12 / 100;

		Double p = ((income * 12 * 50) / 100) * custoumer.get().getCustomerLoanTeanre();

		// monthly intrest rate this field came from front End
		Double emi = p * r * (Math.pow((1 + r), n)) / ((Math.pow((1 + r), n)) - 1);
		sanctionLetter.setMonthlyEmiAmount(emi);

		sanctionLetter.setApplicantName(custoumer.get().getCustomerFirstName() + custoumer.get().getCustomerLastName());
		sanctionLetter.setContactDetails(custoumer.get().getCustomerMobileNumber());
		sanctionLetter.setInterestType("Fixed");
		sanctionLetter.setLoanAmtSanctioned(p);
		sanctionLetter.setLoanTenure(customerLoanTeanre);
		sanctionLetter.setModeOfPayment("Online");
		sanctionLetter.setRateOfInterest(r);
		sanctionLetter.setRemarks("Monthly payment is required");
		String s = custoumer.get().getCustomerLoanFormFillDate();
		String[] split = s.split("-");

		// sanctionLetter.setSanctionDate(custoumer.get().getCustomerLoanFormFillDate());
		sanctionLetter.setStatus("Santion Letter Generated");
		sanctionLetter.setTermsCondition("Terms And Condition Applied");
		custoumer.get().setStatus(sanctionLetter.getStatus());

		// generateSanctionLetterAndSendByMail(sanctionLetter,
		// custoumer.get().getCustomerId());

		sanctionLetterRepository.save(sanctionLetter);

		return sanctionLetter;

	}

	public SanctionLetter generateSanctionLetterAndSendByMailyogesh(Integer customerId, SanctionLetter sanctionLetter) {
		Optional<Customer> custoumer1 = customerLoanFormRepository.findById(customerId);
		Customer custoumer = custoumer1.get();
		Double income = custoumer.getAnnualIncome();
		double customerLoanTeanre = custoumer.getCustomerLoanTeanre();
		Double n = (double) (1 * 12);
		Double intrest =  7d;
		// Float intrest = sanctionLetter.getRateOfInterest();
		Double r = intrest / 12 / 100;

		Double p = ((income * 30) / 100) * custoumer.getCustomerLoanTeanre();

		// monthly intrest rate this field came from front End
		Double emi = p * r * (Math.pow((1 + r), n)) / ((Math.pow((1 + r), n)) - 1);
		sanctionLetter.setMonthlyEmiAmount(emi);

		sanctionLetter.setApplicantName(custoumer.getCustomerFirstName() + custoumer.getCustomerLastName());
		sanctionLetter.setContactDetails(custoumer.getCustomerMobileNumber());
		sanctionLetter.setInterestType("Fixed");
		sanctionLetter.setLoanAmtSanctioned(p);
		sanctionLetter.setLoanTenure(customerLoanTeanre);
		sanctionLetter.setModeOfPayment("Online");
		sanctionLetter.setRateOfInterest(r);
		sanctionLetter.setRemarks("Monthly payment is required");
		String s = custoumer.getCustomerLoanFormFillDate();

		// sanctionLetter.setSanctionDate(custoumer.get().getCustomerLoanFormFillDate());
		sanctionLetter.setStatus("Disbussed_Succsfully");
		sanctionLetter.setTermsCondition("Terms And Condition Applied");

//		For Converting Date To String Data Type
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDateTime = dateFormatter.format(new Date());

		sanctionLetter.setSanctionDate(currentDateTime);
		SanctionLetter sanctionLetter2 = sanctionLetterRepository.save(sanctionLetter);
		custoumer.setSanctionLetter(sanctionLetter2);

		customerLoanFormRepository.save(custoumer);

		logger.info("Create Pdf Started");

		String title = "OMEGA FINANCE SANCTION LETTER";
		String content = "Welcome to OMEGA FINANCE Mr." + custoumer.getSanctionLetter().getApplicantName()
				+ " You fullfill our all term and condition for your loan ."
				+ "that why we are generating your sanction letter with sanction Id "
				+ custoumer.getSanctionLetter().getSanctionId() + " And your sanctioned loan ammount is "
				+ custoumer.getSanctionLetter().getLoanAmtSanctioned() + " with rate of intrest "
				+ custoumer.getSanctionLetter().getRateOfInterest() * 12 * 100 + " for tenure of "
				+ custoumer.getSanctionLetter().getLoanTenure() + "Your Monthly EMI is== "
				+ custoumer.getSanctionLetter().getMonthlyEmiAmount();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();

		PdfWriter.getInstance(document, out);

		document.open();

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26);
		titleFont.setColor(Color.magenta);

		Paragraph titlepara = new Paragraph(title, titleFont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);

		Font parafont = FontFactory.getFont(FontFactory.HELVETICA, 18);
		Paragraph paragraph = new Paragraph(content);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		document.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());

		MimeMessage createMimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage, true);
			mimeMessageHelper.setFrom(reMailid);
			System.out.println(reMailid);
			mimeMessageHelper.setTo(custoumer.getCustomerEmail());
			System.out.println(custoumer.getCustomerEmail());
			mimeMessageHelper.setSubject("OMEGA Car Loan");
			mimeMessageHelper.setText("Sanction Letter");

			byte[] readAllBytes = byteArrayInputStream.readAllBytes();

			mimeMessageHelper.addAttachment("sanctionletter.pdf", new ByteArrayResource(readAllBytes));
			mailSender.send(createMimeMessage);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sanctionLetter2;
	}

	public List<SanctionLetter> findAllBySantionStatus(String status) {
		return sanctionLetterRepository.findAllByStatus("Disbussed_Succsfully");

	}

}
