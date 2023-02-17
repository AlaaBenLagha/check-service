package com.mypfe.serviceInterface;

import java.util.List;

import com.mypfe.common.TransactionRequest;
import com.mypfe.common.TransactionResponse;
import com.mypfe.model.Check;

public interface CheckServiceInterface {
	
	public TransactionResponse addCheckToProcess(TransactionRequest request);
	public List<Check> getAllChecks();
	public Check addCheck(Check check);
	public TransactionResponse addCheckToProcessFallBack(Exception e);

}
