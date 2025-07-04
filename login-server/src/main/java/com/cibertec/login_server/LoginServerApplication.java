package com.cibertec.login_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoginServerApplication.class, args);
	}
}
