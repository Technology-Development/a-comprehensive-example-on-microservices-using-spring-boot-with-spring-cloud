/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */


/* 
 * 
 * N O T E
 * 
 * The Spring Cloud Load Balancer (earlier called Ribbon) is not required 
 * It is because Spring Cloud API Gateway does the load balancing automatically for a microservcie instances
 * 
 */


//package com.airport.flight.loadbalancer;
//
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//
//import feign.Feign;
//
///* airport-pilot-service is the path of airport-pilot-service microservice as per the eureka server console */
//
//@LoadBalancerClient(value = "airport-pilot-service")
//public class PilotServiceLoadBalancer {
//
//	@LoadBalanced
//	@Bean
//	public Feign.Builder feignBuilder() {
//		return Feign.builder();
//	}
//}
