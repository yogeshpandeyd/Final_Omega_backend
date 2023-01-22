package com.myteam.carloanapp.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myteam.carloanapp.app.entity.BaseResponce;
import com.myteam.carloanapp.app.entity.Customer;
import com.myteam.carloanapp.app.entity.Ledger;
import com.myteam.carloanapp.app.repository.CustomerLoanFormRepository;
import com.myteam.carloanapp.app.repository.LedgerRepository;
import com.myteam.carloanapp.app.servicei.LedgerServiceI;

@Service
public class LedgerServiceImpl implements LedgerServiceI {
	Integer ledgerno = ThreadLocalRandom.current().nextInt(850, 899 + 1);

	@Autowired
	LedgerRepository ledgerRepository;

	@Autowired
	CustomerLoanFormRepository customerLoanFormRepository;

//
//	@Override

//
//
//	public Optional<Ledger> getSingleLedgerByLedgerId(Integer ledgerId) 
//	{
//		
//		return ledgerRepository.findById(ledgerId);
//	}
//
//	
//	public List<Ledger> getAllLedger() {
//		
//		return ledgerRepository.findAll();
//	}
//
//	
//	public Ledger updateExistingLedger(Ledger ledger) 
//	{
//		Ledger ledger2 = ledgerRepository.save(ledger);
//		return ledger2;
//	}
//

//	
//	//@Override
//	public List<Ledger> generateLedgeryogesh(Ledger l) {
//		
//		//ledgerRepository.findBySanctionLetter(l.getSanctionLetter());
//		
//		
//		List<Ledger> ledgers=new ArrayList<>();
//		
//		for(int i=1;i<=l.getTotalEmiCount();i++)
//		{ 
//			l.setLedgerCreatedMonth(l.getLedgerCreatedMonth()+1);
//			if(l.getLedgerCreatedMonth()==13)
//			{
//				l.setLedgerCreatedMonth(1);
//				l.setLedgerCreatedYear(l.getLedgerCreatedYear()+1);
//			}
//			      l.setEmiNo(i);
//			
//        		Ledger save = ledgerRepository.save(l);
//        		ledgers.add(save);
//        		l.setSrno(l.getSrno()+1);
//			
//		}
//		l.setSrno(l.getSrno());
//		
//			return ledgers;
//		
//		
//	}

	@Override
	public void closeLedgerAndRequestForNOC(Integer ledgerId) {

		ledgerRepository.deleteById(ledgerId);
	}

	public Ledger generateLedger(Ledger ledger) {
		Ledger ledger2 = ledgerRepository.save(ledger);
		return ledger2;
	}

	@Override
	public String emiunpaid(Integer ledgerid, Integer emino) {
		Ledger ledger = ledgerRepository.findByLedgerIdAndEmiNo(ledgerid, emino);

		ledger.setCurrentMonthEmiStatus("unpaid");
		ledger.setDefaulterCount(1);

		generateLedger(ledger);
		String ss = "EMI no___ " + emino + "Of Ledger ID__  " + ledgerid + " Not Paid";
		return ss;

	}

	@Override
	public String emipaid(Integer ledgerid, Integer emino) {
		Ledger ledger = ledgerRepository.findByLedgerIdAndEmiNo(ledgerid, emino);

		List<Ledger> ledgerno = ledgerRepository.findByLedgerId(ledgerid);
		Stream<Ledger> sorted = ledgerno.stream().sorted((l1, l2) -> -l1.getEmiNo().compareTo(l2.getEmiNo()));
		Optional<Ledger> findFirst = sorted.findFirst();
		if (findFirst.get().getEmiNo() == emino) {

			closeLedgerAndRequestForNOC(ledgerid);

			return "This is your last Emi And you paid Succesfully Hence Your Ledger Is closed permantly";
		}
		ledger.setCurrentMonthEmiStatus("paid");
		ledger.setTotalLoanAmount(ledger.getTotalLoanAmount() - ledger.getMonthlyEmi());

		generateLedger(ledger);
		String s = "EMI no___ " + emino + "___Of Ledger ID____  " + ledgerid + "____Paid Succesfully";
		return s;
	}

	@Override
	public String defaltercount(Integer ledgerid) {

		List<Ledger> ledgers = ledgerRepository.findByLedgerId(ledgerid);

		int count = 0;

		for (Ledger a : ledgers) {

			System.out.println(a);
			if (a.getDefaulterCount() == 0) {
				count = 0;
			}
			if (a.getDefaulterCount() == 1) {
				count++;
				if (count == 3) {

					return "This LedgerId" + ledgerid + "is Defaulter";

				}
			}

		}
		return "This LedgerId" + ledgerid + "is Not Defaulter";

	}

	public List<Ledger> generateLedgernew(Ledger l, Integer custoumerid) {
		Optional<Customer> custoumer = customerLoanFormRepository.findById(custoumerid);
		Integer sanctionId = custoumer.get().getSanctionLetter().getSanctionId();

		List<Ledger> ledgers = new ArrayList<>();
		String s = custoumer.get().getSanctionLetter().getSanctionDate();
		System.err.println(s);
		String[] split = s.split("-");
		String date = split[0];
		String month = split[1];
		String year = split[2];
		Integer date1 = (Integer) new Integer(date);
		Integer month1 = (Integer) new Integer(month);
		Integer year1 = (Integer) new Integer(year);
		for (int i = 1; (i <= custoumer.get().getSanctionLetter().getLoanTenure() * 12); i++) {

			l.setMonthlyEmi(custoumer.get().getSanctionLetter().getMonthlyEmiAmount());
			l.setEmiNo(i);
			l.setCurrentMonthEmiStatus("Generated");

			if (month1 == 12) {
				month1 = 0;
				year1 = year1 + 1;
			}

			l.setLedgerCreatedDate(date1);
			l.setLedgerCreatedMonth(month1 + 1);
			l.setLedgerCreatedYear(year1);
			month1 = month1 + 1;

			l.setLedgerId(ledgerno);
			l.setLoanStatus("Santioned");
			l.setSanctionLetter(custoumer.get().getSanctionLetter());
			// l.setTenure(custoumer.get().getCustomerLoanTeanre());
			l.setTotalEmiCount(custoumer.get().getSanctionLetter().getLoanTenure() * 12);
			l.setTotalLoanAmount(custoumer.get().getSanctionLetter().getLoanAmtSanctioned());

			Double monthlyEmiAmount = custoumer.get().getSanctionLetter().getMonthlyEmiAmount();
			Double customerLoanTeanre = custoumer.get().getCustomerLoanTeanre();
			l.setPayableAmountwithInterest((monthlyEmiAmount * customerLoanTeanre * 12) - (monthlyEmiAmount * (i - 1)));

			Ledger save = ledgerRepository.save(l);
			ledgers.add(save);
			l.setSrno(l.getSrno() + 1);
			custoumer.get().setLedger(ledgers);

		}

		l.setSrno(l.getSrno());

		return ledgers;

	}

}
