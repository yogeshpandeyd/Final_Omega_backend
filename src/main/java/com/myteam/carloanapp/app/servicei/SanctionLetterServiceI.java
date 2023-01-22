package com.myteam.carloanapp.app.servicei;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import com.myteam.carloanapp.app.entity.SanctionLetter;

public interface SanctionLetterServiceI 
{

 public SanctionLetter generateSanctionLetter(SanctionLetter sanctionLetter);

public Optional<SanctionLetter> getSanctionLetterBySanctionId(Integer sanctionId);

public List<SanctionLetter> getListOfAllSanctionLetter();

public void deleteSanctionLetterBySanctionId(Integer sanctionId);

public SanctionLetter saveSanctionLetterAfterUpdate(SanctionLetter sanctionLetter);

public SanctionLetter generateSanctionLetterAndSendByMailgirish(SanctionLetter sanctionLetter,Integer customerId) ;
public SanctionLetter scantionAmmountToCustoumer(Integer custumerId);

public SanctionLetter generateSanctionLetterAndSendByMailyogesh(Integer customerId,SanctionLetter sanctionLetter) ;
public List<SanctionLetter> findAllBySantionStatus(String status);

}
