package com.example.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SqsClient sqsClient;

    private final String queueUrl = "http://localhost:4566/000000000000/order-queue";

    @PostMapping
    public String createOrder(@RequestBody String order) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(order)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
        return "Order submitted: " + order;
    }
}
