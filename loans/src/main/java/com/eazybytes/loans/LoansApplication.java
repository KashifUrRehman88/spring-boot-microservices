package com.eazybytes.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope // This annotation is used when change happened in properties and you want to get reflect those changes instead of re-deploy/restart i.e
//if we used this annotation on individual micro-services, it will enable a new end point on your actuator with name as "/refresh", when ever we invoke this 
//end point, it will reload the properties without need of re-starting services
@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
@EnableJpaRepositories("com.eazybytes.loans.repository")
@EntityScan("com.eazybytes.loans.model")
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}