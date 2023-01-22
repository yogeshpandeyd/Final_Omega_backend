package com.myteam.carloanapp.app.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myteam.carloanapp.app.entity.BaseResponce;
import com.myteam.carloanapp.app.entity.Ledger;
import com.myteam.carloanapp.app.exception.userNotPresentException;
import com.myteam.carloanapp.app.servicei.LedgerServiceI;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ledger")
public class LedgerController 
{

	@Autowired
	LedgerServiceI ledgerServiceI;
	
//	API FOR GENERATING LEDGER BY USING CUSTOMER ID
//	@PostMapping("/generateledger")
//	public ResponseEntity<BaseResponce<List<Ledger>>> generateLedger(@RequestBody Ledger ledger)
//	{
//		List<Ledger> generateLedgeryogesh = ledgerServiceI.generateLedgeryogesh(ledger);
//		BaseResponce<List<Ledger>> base = new BaseResponce<List<Ledger>>(200, "ledger generated", generateLedgeryogesh);
//		return new ResponseEntity<BaseResponce<List<Ledger>>>(base,HttpStatus.OK);
//	}
//	
////	API FOR FIND SINGLE LEDGER BY LEDGER ID
//	@GetMapping("/getsingleledgerbyledgerid/{ledgerId}")
//	public ResponseEntity<BaseResponce<Optional<Ledger>>> getSingleLedgerByLedgerId(@PathVariable ("ledgerId") Integer ledgerId)
//	{
//		Optional<Ledger> singleLedgerByLedgerId = ledgerServiceI.getSingleLedgerByLedgerId(ledgerId);
//		
//		if(singleLedgerByLedgerId.isEmpty())
//		{
//			throw new userNotPresentException("Please Enter Valid LedgerID");
//		}
//		
//		BaseResponce<Optional<Ledger>> base = new BaseResponce<Optional<Ledger>>(201, "Single Ledger Get Successfully", singleLedgerByLedgerId);
//		return ResponseEntity.status(HttpStatus.OK).body(base);
//	}
//	
////	API FOR DISPLAY ALL LEDGER
//	@GetMapping("/getallledger")
//	public ResponseEntity<BaseResponce<List<Ledger>>> getAllLedger()
//	{
//		List<Ledger> allLedger = ledgerServiceI.getAllLedger();
//		BaseResponce<List<Ledger>> base = new BaseResponce<List<Ledger>>(200, "All ledger Data", allLedger);
//		return ResponseEntity.status(HttpStatus.OK).body(base);
//	}
//	
////	API FOR UPDATE LEDGER BY LEDGER ID
//	@PutMapping("/updateledger/{ledgerId}")
//	public ResponseEntity<BaseResponce<Ledger>> updateLedger(@RequestBody Ledger ledger,@PathVariable("ledgerId") Integer ledgerId)
//	{
//		Optional<Ledger> singleLedgerByLedgerId = ledgerServiceI.getSingleLedgerByLedgerId(ledgerId);
//		if(singleLedgerByLedgerId.isPresent())
//		{
//			throw new userNotPresentException("Please Enter Valid LedgerID");
//		}
//		 
//			ledger.setLedgerId(singleLedgerByLedgerId.get().getLedgerId());
//			Ledger ledger2 = ledgerServiceI.generateLedger(ledger);
//			BaseResponce<Ledger> base = new BaseResponce<Ledger>(200, "ledger Update Successfully", ledger2);
//			return ResponseEntity.status(HttpStatus.OK).body(base);
//		
//	}
//	
////	API FOR CLOSING LEDGER AND GENERATE NOC
//	@DeleteMapping("/deleteledgerbyledgerid/{ledgerId}")
//	public ResponseEntity<BaseResponce<Optional<Ledger>>> closeLedgerAndRequestForNOC(@PathVariable ("ledgerId") Integer ledgerId)
//	{
//		Optional<Ledger> singleLedgerByLedgerId = ledgerServiceI.getSingleLedgerByLedgerId(ledgerId);
//		if(singleLedgerByLedgerId.isEmpty())
//		{
//			throw new userNotPresentException("Please Enter Valid LedgerID");
//		}
//		else 
//		{
//			ledgerServiceI.closeLedgerAndRequestForNOC(ledgerId);
//			BaseResponce<Optional<Ledger>> base = new BaseResponce<Optional<Ledger>>(200, "Ledger Closed Successfully And Request Have Been Sent For NOC", null);
//			return ResponseEntity.status(HttpStatus.OK).body(base);
//		}
//	}
	//http://localhost:9999/ledger/emipaid/emiNo/ledgerid
@PostMapping("/emipaid/{emiNo}/{ledgerId}")
public ResponseEntity<BaseResponce<String>> emipaid(@PathVariable ("emiNo") Integer emiNo,@PathVariable("ledgerId") Integer ledgerId)
{
	 String emipaid = ledgerServiceI.emipaid(ledgerId, emiNo);
	BaseResponce<String> base=new BaseResponce<String>(201, "Your"+emiNo+"paid Succesfully", emipaid);
	return new ResponseEntity<BaseResponce<String>>(base, HttpStatus.OK);
}
//http://localhost:9999/ledger/emiunpaid/emiNo/ledgerid
@PostMapping("/emiunpaid/{emiNo}/{ledgerId}")
public ResponseEntity<BaseResponce<String>> emiunpaid(@PathVariable ("emiNo") Integer emiNo,@PathVariable("ledgerId") Integer ledgerId)
{
	String emiunpaid = 	ledgerServiceI.emiunpaid(ledgerId, emiNo);
	BaseResponce<String> base=new BaseResponce<String>(201, "Your"+emiNo+"unpaid ", emiunpaid);
	return new ResponseEntity<BaseResponce<String>>(base, HttpStatus.OK);

}
//http://localhost:9999/ledger/defalter/ledgerid
@GetMapping("/defalter/{ledgerId}")
public ResponseEntity<BaseResponce<String>> defaltercount(@PathVariable ("ledgerId") Integer ledgerId )
{ 
	String defalter =  ledgerServiceI.defaltercount(ledgerId);
	BaseResponce<String> base=new BaseResponce<String>(201, "Your"+ledgerId+"is Defalter", defalter);
	return new ResponseEntity<BaseResponce<String>>(base, HttpStatus.OK);
	
	}

//http://localhost:9999/ledger/generateledgernew/custoumerid
@PostMapping("/generateledgernew/{custoumerid}")
public ResponseEntity<BaseResponce<List<Ledger>>> generateLedgernew(@RequestBody Ledger ledger,@PathVariable ("custoumerid") Integer custoumerid)
{
	List<Ledger> generateLedgeryogesh = ledgerServiceI.generateLedgernew(ledger,custoumerid);
	BaseResponce<List<Ledger>> base = new BaseResponce<List<Ledger>>(200, "ledger generated", generateLedgeryogesh);
	return new ResponseEntity<BaseResponce<List<Ledger>>>(base,HttpStatus.OK);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}