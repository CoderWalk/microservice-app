package com.mypack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayFallbackController {

    //@GetMapping("/orderFallback")
    @RequestMapping("/orderFallback")
    public String orderFallback() {
        return "Order Service is taking too long or is down. Please try again later.";
    }

    @RequestMapping("/paymentFallback")
    public String paymentFallback() {
        return "Payment Service is down. Your order is placed but payment is pending.";
    }
}