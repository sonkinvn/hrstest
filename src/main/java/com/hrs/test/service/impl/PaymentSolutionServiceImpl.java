package com.hrs.test.service.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;
import com.hrs.test.dto.CurrencyConversionDTO;
import com.hrs.test.model.VatAvailability;
import com.hrs.test.repository.VatAvailabilityRepository;
import com.hrs.test.service.PaymentSolutionService;
import com.hrs.test.util.ApiAppResponse;
import com.hrs.test.util.ServiceException;

@Service
public class PaymentSolutionServiceImpl implements PaymentSolutionService {

    @Autowired VatAvailabilityRepository repository;
    
	@Value("${apiLayer.api.convert.url}")
    private String currencyConvertUrl;
	@Value("${apiLayer.apiKey}")
    private String apiKey;
	
	@Value("${cloudmersive.apiKey}")
    private String cloudmersiveApiKey;
	
	@SuppressWarnings("unchecked")
    @Override
	public CurrencyConversionDTO convertCurrency(Double amount, String sourceCurrency, String targetCurrency) {
		String url = String.format(currencyConvertUrl, sourceCurrency, targetCurrency, amount);
		ApiAppResponse resp = callAPI(url, HttpMethod.GET, apiKey, null);
		Map<String, Object> result = (Map<String, Object>) resp.getData();
		if ((boolean)result.get("success")) {
		    return new CurrencyConversionDTO((Object)result.get("result"), targetCurrency);
		} else {
		    Map<String, Object> error = (Map<String, Object>) result.get("error");
		    throw new ServiceException((String) error.get("info"));
		}
	}
	
	@Override
    public VatAvailability validateVATNumber(String vatNumber) {
	    Optional<VatAvailability> vatAvailability = repository.findById(vatNumber);
	    if (vatAvailability.isPresent()) {
	        return vatAvailability.get();
	    } else {
	        return getVatNumber(vatNumber);
	    }
    }
    
	private VatAvailability getVatNumber(String vatNumber) {
	    ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setReadTimeout(300000);
        defaultClient.setConnectTimeout(300000);
        // Configure API key authorization: Apikey
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey(cloudmersiveApiKey);
//      Apikey.setApiKeyPrefix("test-vat-validation");
        VatApi apiInstance = new VatApi(defaultClient);
        VatLookupRequest input = new VatLookupRequest(); // VatLookupRequest | Input VAT code
        input.setVatCode(vatNumber);
        try {
            VatLookupResponse result = apiInstance.vatVatLookup(input);
//            System.out.println(result);
            if (result.isIsValid()) {
                //cache VAT number in database table
                VatAvailability vatAvailability = new VatAvailability();
                BeanUtils.copyProperties(result, vatAvailability);
                vatAvailability.setId(vatAvailability.getCountryCode() + vatAvailability.getVatNumber());
                repository.save(vatAvailability);
                return vatAvailability;
            } else {
                throw new ServiceException(String.format("This VAT number '%s' is invalid.", vatNumber));
            }
        } catch (ApiException e) {
            throw new ServiceException("Error when calling validateVATNumber");
        }
	}
	
	private ApiAppResponse callAPI(String url, HttpMethod method, String apikey, Object body){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Application");
        headers.add("apikey", apikey);
        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
        ResponseEntity<?> response = restTemplate.exchange(url, method, entity, Object.class);
        return new ApiAppResponse(response, true);
    }
	
}
