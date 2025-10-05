package com.example.demo.service;

import com.example.demo.exception.AreqProcessingException;
import com.example.demo.model.AreqRequest;
import com.example.demo.model.AresResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AreqService {

	@Autowired
	private RestTemplate restTemplate;

	public AresResponse processAreq(AreqRequest areqRequest) {
		// Validate and process the AReq request
		// This is a simplified example, adjust the logic as needed
		AresResponse aresResponse = new AresResponse();
		aresResponse.setThreeDSServerTransID(areqRequest.getThreeDSServerTransID());
		aresResponse.setDsTransID(areqRequest.getDsTransID());
		aresResponse.setMessageVersion(areqRequest.getMessageVersion());
		aresResponse.setMessageType("ARes");
		aresResponse.setAcsTransID("sample-acs-trans-id");
		aresResponse.setTransStatus("Y");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("\n Adding logs chnages for testing purpose \n");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// Post the response back to the dsURL (asynchronously or synchronously)
		//postAresResponse(aresResponse, areqRequest.getDsURL());
			
			return aresResponse;
	}

	private void postAresResponse(AresResponse aresResponse, String dsURL) {
		try {
			 restTemplate.postForEntity(dsURL, aresResponse, Void.class);

		} catch (Exception e) {
			// Handle exceptions (e.g., logging)
			throw new AreqProcessingException("Error connecting to DS at ",e);
		}
		
	}
}
