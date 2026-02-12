package com.mypack.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    // 1. Provide a LoadBalanced Builder
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    // 2. Build the specific bean for your service
    @Bean
    public RestClient paymentRestClient(RestClient.Builder builder) {
        // This 'builder' is the one injected from above (LoadBalanced)
        return builder.baseUrl("http://Payment-Service").build();
    }
}
