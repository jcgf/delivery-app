package com.jcgfdev.deliveryapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryAppApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DeliveryAppApplication.class);
        app.run(args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
