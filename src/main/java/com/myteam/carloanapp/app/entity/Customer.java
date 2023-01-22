package com.myteam.carloanapp.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	private Integer customerId;
	private String customerFirstName;
	private String customerMiddleName;
	private String customerLastName;
	private String customerEmail;
	private Long customerMobileNumber;
	private Long customerAlternateMobileNumber;
	private String customerPanCard;
	private Long customerAadharCard;
	//changed........added
	private String customerLoanFormFillDate;
	private String customerGender;
	private Integer customerCibilScore;
	
	private String customerQualification;
	//change.......added
	private Double annualIncome;
	private Double  customerLoanTeanre;
	
	
	private String status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;
	
    @OneToOne(cascade = CascadeType.ALL)
	private Profession customerProfession;
    
    @OneToOne(cascade = CascadeType.ALL)
    private CustomerBankDetails customerBankDetails;
    
    @OneToOne(cascade = CascadeType.ALL)
    private VehicleDetails vehicleDetails;
    
	@OneToOne(cascade = CascadeType.ALL)
	private DealerBankDetails dealerBankDetails;
								
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAllDocuments customerAllDocuments ;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private EducationalInformation educationalInfo;	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Cibil cibilscore;
	@OneToOne(cascade = CascadeType.ALL)
	private CurrentLoanDetails currentLoanDetails;
	@OneToOne(cascade = CascadeType.ALL)

	private LoanDisbursement loanDisbursement;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ledger> ledger;
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;
//	@OneToOne(cascade = CascadeType.ALL)
//	private CustomerVerification customerVerification;
	  
	

//	@OneToOne(cascade = CascadeType.ALL)
//	private AllPersonalDocuments allPersonalDoc;
//	@OneToOne(cascade = CascadeType.ALL)
//	private FamilyDependentInforamtion familyDependentInfo;
	
//	private ShowroomAccountDetails accountDetails;
//	@OneToOne(cascade = CascadeType.ALL)
//	private CarInfo customerCarVerification;
//	@OneToOne(cascade = CascadeType.ALL)

	
	
}
