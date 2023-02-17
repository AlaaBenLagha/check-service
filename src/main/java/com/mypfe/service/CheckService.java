package com.mypfe.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mypfe.common.Processing;
import com.mypfe.common.TransactionRequest;
import com.mypfe.common.TransactionResponse;
import com.mypfe.model.Check;
import com.mypfe.model.CheckStatus;
import com.mypfe.repository.CheckRepository;
import com.mypfe.serviceInterface.CheckServiceInterface;


@Service
public class CheckService implements CheckServiceInterface {

	@Autowired
	private CheckRepository checkRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String PROCESSBASEURL = "http://PROCESS-SERVICE/process";

	

	
	@Override
	public TransactionResponse addCheckToProcess(TransactionRequest request) {
		String response = "";
		Check check = request.getCheck();
	
			check.setCheckStatus(CheckStatus.UP);
			Processing processing = request.getProcessing();
			processing.setCheckId(check.getCheckId());
			// Rest Class
			Processing processingResponse =  restTemplate.postForObject(PROCESSBASEURL + "/doProcess", processing, Processing.class);
			
			
			//Processing
			response = processingResponse.getProcessStatus().equals("UP")?"Check Processing Active":"Check Processing Blocked";
			
			checkRepo.save(check);
			
			return new TransactionResponse(check,processingResponse.getTransactionId(), response);

		
	}
	
//	private CheckStatus randomCheckStatus() {
//        int randomIndex = new Random().nextInt(CheckStatus.values().length);
//        return CheckStatus.values()[randomIndex];
//    }
	
	
	@Override
	public List<Check> getAllChecks() {
		return checkRepo.findAll();
	}

	@Override
	public Check addCheck(Check check) {
		check.setCheckStatus(CheckStatus.DOWN);
		return checkRepo.save(check);
	}
//	hard code for TransactionResponse 
	@Override
	public TransactionResponse addCheckToProcessFallBack(Exception e) {
		Check check = new Check(00,"ThisCheckIsActiveButProcessingServiceUnvailable" , CheckStatus.UP);
	        String transactionId = "ThisTransactionIsUnavailable";
	        String message = "Connection with Processing system failed";
		 		
		 return new TransactionResponse(check,transactionId,message);
	}
	
	
	


}
