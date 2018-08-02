package com.test.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@Configuration
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	
	/*@Bean
	public SecurityWebFilterChain securityWebFilterChain(
	  ServerHttpSecurity http) {
	    return http.authorizeExchange()
	      .pathMatchers("/actuator/**").permitAll()
	      .anyExchange().authenticated()
	      .and().build();
	}*/
}
