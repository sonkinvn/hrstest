package com.hrs.test.service;

import com.hrs.test.dto.CurrencyConversionDTO;
import com.hrs.test.model.VatAvailability;

public interface PaymentSolutionService {

    CurrencyConversionDTO convertCurrency(Double amount, String sourceCurrency, String targetCurrency);

    VatAvailability validateVATNumber(String vatNumber);

}
