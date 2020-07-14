package com.thomas.connectionToAnExistingDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			if(repository.findByLastName("Bauer").size() == 0) {
				log.info("No Customer with lastname Bauer was found");
				log.info("The application will add one now");
				repository.save(new Customer("Jack", "Bauer"));
			} else {
				log.info("a Customer with lastname Bauer was found");
				Customer customer = repository.findByLastName("Bauer").get(0);
				log.info(customer.toString());
			}
		};
	}

}
