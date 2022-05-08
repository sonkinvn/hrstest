package com.hrs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.hrs.test.dto.CurrencyConversionDTO;
import com.hrs.test.dto.JsonResponse;
import com.hrs.test.model.VatAvailability;
import com.hrs.test.resource.PaymentSolutionResource;
import com.hrs.test.service.impl.PaymentSolutionServiceImpl;


class PaymentSolutionResourceImplTest extends BaseTest {

  @TestConfiguration
  static class PaymentSolutionServiceImplTestConfiguration {
    @Bean
    PaymentSolutionResource testService() {
      return new PaymentSolutionResource();
    }
  }

  @MockBean private PaymentSolutionServiceImpl paymentSolutionService;

  @Autowired private PaymentSolutionResource paymentSolutionResource;
  
  @Test
  public void testLucky() {
      assertEquals(7, 7);
  }
  
  @Test
  void testConvertCurrency() {
      when(paymentSolutionService.convertCurrency(10D, "USD", "VND"))
          .thenReturn(new CurrencyConversionDTO(230000, "VND"));
      JsonResponse result = paymentSolutionResource.convertCurrency(10D, "USD", "VND");
      assertNotNull(result);
      assertNotNull(result.getResult());

      CurrencyConversionDTO responseBody = (CurrencyConversionDTO) result.getResult();
      assertNotNull(responseBody);
      assertEquals(responseBody.getTargetCurrency(), "VND");
  }
  
  @Test
  void testvalidateVATNumber() {
      when(paymentSolutionService.validateVATNumber("LU20260743"))
          .thenReturn(new VatAvailability());
      JsonResponse result = paymentSolutionResource.validateVATNumber("LU20260743");
      assertNotNull(result);
      assertNotNull(result.getResult());
  }
  
}
