package com.INetwork.corebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoreBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreBankingApplication.class, args);
	}

}
