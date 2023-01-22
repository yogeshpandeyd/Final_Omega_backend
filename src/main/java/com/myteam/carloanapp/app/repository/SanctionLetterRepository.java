package com.myteam.carloanapp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myteam.carloanapp.app.entity.SanctionLetter;
@Repository
public interface SanctionLetterRepository extends JpaRepository<SanctionLetter, Integer>
{

	public List<SanctionLetter> findAllByStatus(String status);

}
