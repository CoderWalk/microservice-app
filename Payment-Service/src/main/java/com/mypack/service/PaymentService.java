package com.mypack.service;

import com.mypack.entity.Payment;
import com.mypack.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    public Payment doPayment(Payment payment)
    {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }


    public String paymentProcessing(){
        //api call should be third party payment gateway
        return new Random().nextBoolean()? "success" :"false";
    }

    public Payment findOrderHistoryById(int orderId){
        return repository.findByOrderId(orderId);
    }
}
