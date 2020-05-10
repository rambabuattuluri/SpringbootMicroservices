package com.bootcamp.microservice.cartauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class CartAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartAuthServerApplication.class, args);
	}

}
