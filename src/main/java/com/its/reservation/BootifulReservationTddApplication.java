package com.its.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootifulReservationTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootifulReservationTddApplication.class, args);
	}
}
