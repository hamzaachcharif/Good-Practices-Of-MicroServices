package org.openlab.ensetbillingservice;

import org.openlab.ensetbillingservice.dto.InvoiceRequestDTO;
import org.openlab.ensetbillingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class EnsetBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetBillingServiceApplication.class, args);


    }
    @Bean
    CommandLineRunner start(InvoiceService invoiceService){
        return  args -> {

            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(84512255),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(854556),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(122556),"C01"));
        };
    }

}
