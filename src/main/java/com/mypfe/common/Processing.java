package com.mypfe.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//As DTO Not Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Processing{

	private int processId;
	private int checkId;
	private String processStatus;
	private String transactionId;	
	
	
}
