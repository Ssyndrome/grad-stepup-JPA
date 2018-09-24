package com.thoughtworks.jpadatapractice;

import com.thoughtworks.jpadatapractice.entity.Customer;
import com.thoughtworks.jpadatapractice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDataPracticeApplication {

    private static final Logger log = LoggerFactory.getLogger(JpaDataPracticeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaDataPracticeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository){
        return args -> {
            repository.save(new Customer("Ya", "Xu"));
            repository.save(new Customer("Ming", "Sun"));
            repository.save(new Customer("Ming", "Chen"));
            repository.save(new Customer("Kai", "Yan"));
            repository.save(new Customer("Miao", "Xu"));

            log.info("Customer found with findAll() :");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            repository.findById(1L).ifPresent(customer -> {
                log.info("Customer found with findById() :");
                log.info("--------------------------------");
                log.info(customer.toString());
                log.info("");
            });

            log.info("Customer found with findByLastName() :");
            repository.findByLastName("Xu").forEach(customer -> {
                log.info("--------------------------------------");
                log.info(customer.toString());
            });
            log.info("");
        };
    }
}
