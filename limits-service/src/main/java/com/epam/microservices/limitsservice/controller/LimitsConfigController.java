/**
 * 
 */
package com.epam.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.microservices.limitsservice.bean.LimitConfiguration;
import com.epam.microservices.limitsservice.config.Config;

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
}
