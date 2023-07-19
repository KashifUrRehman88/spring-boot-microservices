/**
 * 
 */
package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.config.AccountsServiceConfig;
import com.eazybytes.accounts.model.*;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class AccountsController {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	AccountsServiceConfig accountsServiceConfig;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	/**
	 * 
	 * 
	 * -> When some one hit this URL, it will give all the properties based upon the current environment , that account micro-service is being run
	 * With this below method(service) , account services has all information on how to read the properties from a config server i.e. (below are pre-reqs)
	 * 1. add pom dependencies (spring cloud starter config)
	 * 2. update the application.properties (which indicates from where microservice has to read the configurations and what is the active environment right now i want to start my application)
	 * 3. created properties model class and accountserviceconfig class
	 * 4. create a new API (which is below one), of type Getmapping , where some one can call and read all properties
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
				accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
		
		// Using Jackson library, we are trying to convert the properties object into a JSONB format, so that we can display user into a JSON format
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}