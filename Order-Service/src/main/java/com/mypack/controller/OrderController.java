package com.mypack.controller;

import com.mypack.DTO.Payment;
import com.mypack.DTO.TransactionRequest;
import com.mypack.DTO.TransactionResponse;
import com.mypack.entity.Order;
import com.mypack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;


    /*@PostMapping("/bookOrder")
    public Order saveOrder(@RequestBody Order order)
    {
        return service.saveOrder(order);
    }*/

    @PostMapping("/bookOrder")
    public TransactionResponse saveOrder(@RequestBody TransactionRequest request)
    {
        return service.saveOrder(request);
    }
}
