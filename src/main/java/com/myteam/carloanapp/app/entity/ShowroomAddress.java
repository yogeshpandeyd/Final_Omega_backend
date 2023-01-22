package com.myteam.carloanapp.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ShowroomAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer showroomAddressId;
  private String cityname;
  private String district;
  private String state;
}
