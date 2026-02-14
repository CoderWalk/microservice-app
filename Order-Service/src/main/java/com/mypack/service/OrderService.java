package com.mypack.service;

import com.mypack.DTO.Payment;
import com.mypack.DTO.TransactionRequest;
import com.mypack.DTO.TransactionResponse;
import com.mypack.config.RestClientConfig;
import com.mypack.entity.Order;
import com.mypack.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    RestClient restClient;

    @Autowired
    RestClientConfig config;

    public TransactionResponse saveOrder(TransactionRequest request)
    {
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // Using RestClient to perform the POST call
        Payment paymentResponse = restClient.post()
                .uri(config.getDoPaymentPath())
                .body(payment)
                .retrieve()
                .body(Payment.class);
        String message = (paymentResponse.getPaymentStatus().equals("success"))?"payment processing , order placed":"there is a failure in payment api, order added to cart";
        repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);
    }


}
