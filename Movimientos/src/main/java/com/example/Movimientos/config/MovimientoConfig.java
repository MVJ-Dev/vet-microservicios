package com.example.Movimientos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovimientoConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
