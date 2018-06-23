/**
 * 
 */
package com.epam.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.microservices.limitsservice.bean.LimitConfiguration;
import com.epam.microservices.limitsservice.config.Config;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Krishnakanth_Yachare
 *
 */
@RestController
public class LimitsConfigController {

	@Autowired
	private Config configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(),
				configuration.getMinimum());

	}
	// Fault Tolerence with Hystrix
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		// LOgic If exception occurss
		throw new RuntimeException("Not available");
	}

	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(999, 9);
	}
}
