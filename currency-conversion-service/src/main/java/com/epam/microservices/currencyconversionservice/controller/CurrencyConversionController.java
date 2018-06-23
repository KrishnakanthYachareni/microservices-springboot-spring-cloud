/**
 * 
 */
package com.epam.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.epam.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

/**
 * @author KrishnaKanth
 *
 */
@RestController
public class CurrencyConversionController {
	private Logger logger = LoggerFactory
			.getLogger(CurrencyConversionController.class);

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {

		/*
		 * Feign Problem- Unnecessary code to avoid we have to use Feign- just
		 * we need to create the Proxy of depended Rest Service Uri and then use
		 * it.
		 */
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
				.getForEntity(
						"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
						CurrencyConversionBean.class, uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to,
				response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(
			@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// Feign Problem-Solution
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to,
				response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());
	}
}
