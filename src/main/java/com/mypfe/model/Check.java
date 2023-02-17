package com.mypfe.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="CHK_TB")
public class Check{
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int checkId;
	private String name;
	private CheckStatus checkStatus;
	
	

}
	