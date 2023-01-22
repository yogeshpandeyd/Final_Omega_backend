package com.myteam.carloanapp.app.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import com.myteam.carloanapp.app.entity.SanctionLetter;
import com.myteam.carloanapp.app.exception.userNotPresentException;
import com.myteam.carloanapp.app.servicei.SanctionLetterServiceI;

@CrossOrigin("*")
@RestController
@RequestMapping("/sanctionletter")
public class SanctionLetterController 
{

	@Autowired
	SanctionLetterServiceI sanctionLetterServiceI;
 
////	API FOR GENERATE SANCTION LETTER WITH RESPECTED 
//	@PostMapping("/generatesanctionletter")
//	public ResponseEntity<BaseResponce<SanctionLetter>> generateSanctionLetter(@RequestBody SanctionLetter sanctionLetter)
//	{
//		SanctionLetter sanctionLetter2 = sanctionLetterServiceI.generateSanctionLetter(sanctionLetter);
//		BaseResponce<SanctionLetter> base = new BaseResponce<SanctionLetter>(200, "Sanction Letter Generated Successfully!!!", sanctionLetter2);
//		return ResponseEntity.status(HttpStatus.CREATED).body(base);
//	}
//
////	API FOR FINDING SANCTION LETTER WITH SANCTION ID
//	@GetMapping("/getsanctionletterbysanctionid/{sanctionId}")
//	public ResponseEntity<BaseResponce<Optional<SanctionLetter>>> getSanctionLetterBySanctionId(@PathVariable ("sanctionId") Integer sanctionId)
//	{
//		Optional<SanctionLetter> sanctionLetterBySanctionId = sanctionLetterServiceI.getSanctionLetterBySanctionId(sanctionId);
//		if(sanctionLetterBySanctionId.isEmpty())
//		{
//			throw new userNotPresentException("Sanction Letter With Given Sanction Id Not Available!!!!");
//		}
//		
//		BaseResponce<Optional<SanctionLetter>> base = new BaseResponce<Optional<SanctionLetter>>(200, "This Is Sanction Letter With Given SanctionId", sanctionLetterBySanctionId);
//		return ResponseEntity.status(HttpStatus.OK).body(base);
//	}
//
////	API FOR SHOWING ALL SANCTION LETTER 
//	@GetMapping("/getalldanctionletter")
//	public ResponseEntity<BaseResponce<List<SanctionLetter>>> getListOfAllSanctionLetter()
//	{
//		List<SanctionLetter> listOfAllSanctionLetter = sanctionLetterServiceI.getListOfAllSanctionLetter();
//		BaseResponce<List<SanctionLetter>> base = new BaseResponce<List<SanctionLetter>>(200,"hh",listOfAllSanctionLetter);
//		return new ResponseEntity<BaseResponce<List<SanctionLetter>>>(base,HttpStatus.OK);
//	}
//	
////	API FOR DELETING SANCTION LETTER
//	@DeleteMapping("/deletesanctionletterbysanctionid/{sanctionId}")
//	public ResponseEntity<BaseResponce<Optional<SanctionLetter>>> deleteSanctionLetterBySanctionId(@PathVariable ("sanctionId") Integer sanctionId)
//	{
//		Optional<SanctionLetter> sanctionLetterBySanctionId = sanctionLetterServiceI.getSanctionLetterBySanctionId(sanctionId);
//		if(sanctionLetterBySanctionId.isEmpty())
//		{
//			throw new userNotPresentException("Sanction Letter With Given Sanction Id Is Not Available");
//		}
//		sanctionLetterServiceI.deleteSanctionLetterBySanctionId(sanctionId);
//		BaseResponce<Optional<SanctionLetter>> base = new BaseResponce<Optional<SanctionLetter>>(200, "Sanction Letter With Given SanctionId Deleted Successfully!!!", null);
//		return  ResponseEntity.status(HttpStatus.OK).body(base);
//	}
//	
////	API FOR UPDATING SANCTION LETTER
//	@PutMapping("/updatesanctionletter/{sanctionId}")
//	public ResponseEntity<BaseResponce<SanctionLetter>> upadateSanctionLetter(@PathVariable ("sanctionId") Integer sanctionId ,@RequestBody SanctionLetter sanctionLetter)
//	{
//		Optional<SanctionLetter> sanctionLetterBySanctionId = sanctionLetterServiceI.getSanctionLetterBySanctionId(sanctionId);
//		if(sanctionLetterBySanctionId.isEmpty())
//		{
//			throw new userNotPresentException("Sanction Letter With Given Sanction Id Is Not Available");
//		}
//		sanctionLetter.setSanctionId(sanctionLetterBySanctionId.get().getSanctionId());
//		SanctionLetter generateSanctionLetter = sanctionLetterServiceI.generateSanctionLetter(sanctionLetter);
//		BaseResponce<SanctionLetter> base = new BaseResponce<SanctionLetter>(200, "Sanction Letter With Given SanctionId Updated Successfully!!!", generateSanctionLetter);
//		return new ResponseEntity<BaseResponce<SanctionLetter>>(base,HttpStatus.OK);
//	}
//	
////Do NOT USE by girish	API FOR GENERATING SANCTION LETTER AND SEND ON MAIL
//	@PostMapping("generatesantionletter/{customerId}")
//	public ResponseEntity<BaseResponce<SanctionLetter>> generateSanctionLetterAndSendByMail(@RequestBody SanctionLetter s, @PathVariable ("customerId") Integer customerId)
//	{
//		SanctionLetter generateSanctionLetterAndSendByMail = sanctionLetterServiceI.generateSanctionLetterAndSendByMailgirish(s, customerId);
//		BaseResponce<SanctionLetter> base = new BaseResponce<SanctionLetter>(200, "Sanction Letter Generated Successfully!!!", generateSanctionLetterAndSendByMail);
//		return ResponseEntity.status(HttpStatus.CREATED).body(base);
//	}
//	
//	
//	
	
//
//	@PostMapping("generatesantionletterfinal/{customerId}/{intrestrate}")
//	public ResponseEntity<BaseResponce<SanctionLetter>> generateSanctionLetterAndSendByMailyogesh(@RequestBody SanctionLetter s,@PathVariable ("customerId") Integer customerId,@PathVariable ("intrestrate") Double intrestrate)
//	{
//		SanctionLetter generateSanctionLetterAndSendByMail = sanctionLetterServiceI.generateSanctionLetterAndSendByMailyogesh(customerId,s,intrestrate);
//		BaseResponce<SanctionLetter> base = new BaseResponce<SanctionLetter>(200, "Sanction Letter Generated Successfully!!!", generateSanctionLetterAndSendByMail);
//		return ResponseEntity.status(HttpStatus.CREATED).body(base);
//		
//	}
	
	//http://localhost:9999/sanctionletter/disbusmentsucces/Disbussed_Succsfully
	@GetMapping("/disbusmentsucces/{status}")
	 public ResponseEntity<BaseResponce<List<SanctionLetter>>>  findAllBySantionStatus(@PathVariable ("status") String status)
	 {
		 List<SanctionLetter> findAllBySantionStatus = sanctionLetterServiceI.findAllBySantionStatus(status);
		 BaseResponce<List<SanctionLetter>> base=new BaseResponce<List<SanctionLetter>>(201,"All_Disbussed_List" , findAllBySantionStatus);
		 ResponseEntity<BaseResponce< List<SanctionLetter>>> re=new ResponseEntity<BaseResponce<List<SanctionLetter>>>(base,HttpStatus.OK);
		 return re;
	 }
	

	@PostMapping("generatesantionletterfinal/{customerId}")
	public ResponseEntity<BaseResponce<SanctionLetter>> generateSanctionLetterAndSendByMailyogesh(@RequestBody SanctionLetter s,@PathVariable ("customerId") Integer customerId)
	{
		SanctionLetter generateSanctionLetterAndSendByMail = sanctionLetterServiceI.generateSanctionLetterAndSendByMailyogesh(customerId,s);
		BaseResponce<SanctionLetter> base = new BaseResponce<SanctionLetter>(200, "Sanction Letter Generated Successfully!!!", generateSanctionLetterAndSendByMail);
		return ResponseEntity.status(HttpStatus.CREATED).body(base);
		
	}
	
	
}
