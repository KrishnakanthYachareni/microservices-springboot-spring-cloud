/**
 * 
 */
package com.epam.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.epam.microservices.currencyconversionservice.bean.CurrencyConversionBean;

/**
 * @author Krishna
 *
 */
/*
 * Hard coded one replace with Ribbon-client enable us to distribute the load
 * among th all the servers of Currency Exchange Server instances
 * 
 * @FeignClient(name = "currency-exchange-service", url = "localhost:8000")
 */

@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			@PathVariable("from") String from, @PathVariable("to") String to);

}
