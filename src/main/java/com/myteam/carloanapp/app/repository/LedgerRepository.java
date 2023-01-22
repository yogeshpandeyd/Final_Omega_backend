package com.myteam.carloanapp.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myteam.carloanapp.app.entity.Ledger;
import com.myteam.carloanapp.app.entity.SanctionLetter;

@Repository
@EnableJpaRepositories

public interface LedgerRepository extends JpaRepository<Ledger, Integer>
{

	public Ledger findByLedgerIdAndEmiNo(Integer ledgerid,Integer emino);
	
	public List<Ledger> findByLedgerId(Integer ledgerid);
	public SanctionLetter findBySanctionLetter(SanctionLetter sanctionLetter);
	

	

}
