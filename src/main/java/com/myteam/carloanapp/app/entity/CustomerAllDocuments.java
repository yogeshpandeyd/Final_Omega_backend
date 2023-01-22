package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerAllDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer documentID;
	
	@Lob
	private byte[] panCard;
	
	@Lob
	private byte[] incomeProof;
	@Lob
	private byte[] adhaarCard;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] signature;
	@Lob
	private byte[] bankPassBook;
}
