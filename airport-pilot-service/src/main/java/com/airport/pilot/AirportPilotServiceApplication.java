package com.airport.pilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.airport.pilot.controller", "com.airport.pilot.service", "com.airport.pilot.exception"})
@EntityScan("com.airport.pilot.entity")
//@EnableJpaRepositories("com.airport.pilot.repository")
@EnableEurekaClient
public class AirportPilotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportPilotServiceApplication.class, args);
	}

}
