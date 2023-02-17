package com.mypfe.common;

import com.mypfe.model.Check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

	
	private Check check;
	private Processing processing;
}
