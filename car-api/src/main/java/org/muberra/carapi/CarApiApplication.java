package org.muberra.carapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApiApplication.class, args);
	}

}
