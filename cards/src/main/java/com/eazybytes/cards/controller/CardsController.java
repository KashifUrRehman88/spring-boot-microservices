/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.cards.config.CardsServiceConfig;
import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.model.Properties;
import com.eazybytes.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Kashif Ur Rehman
 *
 */

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	CardsServiceConfig cardsServiceConfig;

	@PostMapping("/myCards")
	public List<Cards> getAccountDetails(@RequestBody Customer customer) {

		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

	@GetMapping("/card/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(),
				cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());
		
		// Using Jackson library, we are trying to convert the properties object into a JSONB format, so that we can display user into a JSON format
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
}