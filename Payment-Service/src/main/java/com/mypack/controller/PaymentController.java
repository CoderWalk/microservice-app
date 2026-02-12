package com.mypack.controller;

import com.mypack.entity.Payment;
import com.mypack.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment)
    {
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findOrderHistoryById(@PathVariable  int orderId){
        return service.findOrderHistoryById(orderId);
    }

}
