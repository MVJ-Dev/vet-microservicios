package com.example.eureka_server.config;

import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;
import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaTransportConfig {

    @Bean
    public TransportClientFactories<?> transportClientFactories() {
        return Jersey3TransportClientFactories.getInstance();
    }
}
