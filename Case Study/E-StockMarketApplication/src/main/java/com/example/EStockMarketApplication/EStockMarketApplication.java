package com.example.EStockMarketApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class  EStockMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStockMarketApplication.class, args);
	}

}
