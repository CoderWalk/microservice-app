package com.mypack.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;

@Configuration
@RefreshScope
public class RestClientConfig {


    @Value("${microservice.payment-service.base-url}")
    private String paymentBaseUrl;

    @Getter
    @Value("${microservice.payment-service.endpoint-path}")
    private String doPaymentPath;
    // 1. Provide a LoadBalanced Builder
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }


    // 2. Build the specific bean for your service
    /*@Bean
    public RestClient paymentRestClient(RestClient.Builder builder) {
        // This 'builder' is the one injected from above (LoadBalanced)
        //return builder.baseUrl("http://Payment-Service").build();
        return builder.baseUrl(paymentBaseUrl).build();
    }*/
    @Bean
    public RestClient paymentRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        // Ab Spring ko pata hai ki 'loadBalanced' wala hi use karna hai
        return builder.baseUrl(paymentBaseUrl).build();
    }
}
