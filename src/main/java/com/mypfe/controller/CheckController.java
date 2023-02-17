package com.mypfe.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mypfe.common.TransactionRequest;
import com.mypfe.common.TransactionResponse;
import com.mypfe.model.Check;
import com.mypfe.model.CheckStatus;
import com.mypfe.serviceInterface.CheckServiceInterface;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/check")
public class CheckController {

	@Autowired
	private CheckServiceInterface checkSerivceInterface;
	
	public static final String CHECK_SERVICE = "check-service";
	private int attemps = 1 ;
	
	
	@PostMapping("/addCheckToProcess")
	@CircuitBreaker(name =CHECK_SERVICE, fallbackMethod = "addCheckToProcessFallBack")
	@Retry(name = CHECK_SERVICE, fallbackMethod = "addCheckToProcessFallBack" ) 
	public TransactionResponse addCheckToProcess(@RequestBody TransactionRequest request) {
		System.out.println("retry method called " + attemps++ + " times " + " at " + new Date());
		return checkSerivceInterface.addCheckToProcess(request);
	}
//	hard code for TransactionResponse 
	public TransactionResponse addCheckToProcessFallBack(Exception e) {
		 return checkSerivceInterface.addCheckToProcessFallBack(e);
	}

	@GetMapping("/getAllChecks")
	public List<Check> getAllChecks() {
		return checkSerivceInterface.getAllChecks();
	}
	
	
	@PostMapping("/addCheck")
	public Check addCheck(@RequestBody Check check) {
		check.setCheckStatus(CheckStatus.DOWN);
		return checkSerivceInterface.addCheck(check);
	}
	
	
}
