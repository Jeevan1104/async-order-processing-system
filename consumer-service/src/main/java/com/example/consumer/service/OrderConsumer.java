package com.example.consumer.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class OrderConsumer {

    private final SqsClient sqsClient = SqsClient.create();
    private final String queueUrl = "http://localhost:4566/000000000000/order-queue";

    @Scheduled(fixedRate = 5000)
    public void pollQueue() {
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(5)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
        for (Message message : messages) {
            System.out.println("Processing order: " + message.body());
            sqsClient.deleteMessage(DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(message.receiptHandle())
                    .build());
        }
    }
}
