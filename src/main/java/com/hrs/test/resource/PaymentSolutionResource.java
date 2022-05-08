package com.hrs.test.resource;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.test.dto.JsonResponse;
import com.hrs.test.service.PaymentSolutionService;
import com.hrs.test.util.ServiceException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PaymentSolutionResource {

	@Autowired 
	private PaymentSolutionService service;
	
	@GetMapping(value="/current-time")
    public JsonResponse getCurrentTime() {
        JsonResponse response = new JsonResponse();
        response.setStatus(JsonResponse.SUCCESS);
        response.setResult(Instant.now());
        return response;
    }
	
	@GetMapping(value="/currency/convert")
    public JsonResponse convertCurrency(
          @RequestParam(value="amount", required = true) Double amount,
          @RequestParam(value="sourceCurrency", required = true) String sourceCurrency,
          @RequestParam(value="targetCurrency", required = true) String targetCurrency) {
	    JsonResponse response = new JsonResponse();
        response.setStatus(JsonResponse.SUCCESS);
        try {
            response.setResult(service.convertCurrency(amount, sourceCurrency, targetCurrency));
        } catch (ServiceException e) {
            log.error("Error when calling convertCurrency {}", e.getMessage());
            response.setStatus(JsonResponse.ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
	
	@GetMapping(value="/vat/validate")
    public JsonResponse validateVATNumber(
          @RequestParam(value="vatNumber", required = true) String vatNumber) {
        JsonResponse response = new JsonResponse();
        response.setStatus(JsonResponse.SUCCESS);
        try {
            response.setResult(service.validateVATNumber(vatNumber));
        } catch (ServiceException e) {
            log.error("Error when calling validateVATNumber {}", e.getMessage());
            response.setStatus(JsonResponse.ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
	
}