/**
 * 
 */
package com.epam.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epam.microservices.currencyexchangeservice.bean.ExchangeValue;

/**
 * @author Krishna
 *
 */
public interface ExchangeValueRepository
		extends
			JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
