package com.myteam.carloanapp.app.servicei;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.myteam.carloanapp.app.entity.Ledger;

@Component
public interface LedgerServiceI 
{

public 	Ledger generateLedger(Ledger ledger);

//public Optional<Ledger> getSingleLedgerByLedgerId(Integer ledgerId);

//public List<Ledger> getAllLedger();

//public Ledger updateExistingLedger(Ledger ledger);

public void closeLedgerAndRequestForNOC(Integer ledgerId);

//public List<Ledger> generateLedgeryogesh(Ledger l);

public String emipaid(Integer ledgerid,Integer emino);

public String emiunpaid(Integer ledgerid,Integer emino);

public String defaltercount(Integer ledgerid);

public List<Ledger> generateLedgernew(Ledger l,Integer custoumerid);

}
