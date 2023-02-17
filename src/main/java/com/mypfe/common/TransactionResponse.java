package com.mypfe.common;

import com.mypfe.model.Check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
	
	private Check check;
	private String transactionId;
	private String message;

}
