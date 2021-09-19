package com.airport.api.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class APIGatewayFilter implements GlobalFilter {

	Logger logger = LoggerFactory.getLogger(APIGatewayFilter.class);

	/* We are trying to log all the request header (x-api-key) of all the microservices via API gateway */

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		/* API Gateway is now receive the request and doing some pre-filter */

		ServerHttpRequest request = exchange.getRequest();

		logger.info("x-api-key = " + request.getHeaders().getFirst("x-api-key"));

		/*
		 * Once pre-filter is applied on request it will be forwarded to appropriate MS
		 */

		return chain.filter(exchange);
	}

}
