/*
 * Copyright Symsoft AB 1996-2015. All Rights Reserved.
 */
package se.symsoft.codecamp.myservice;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class SqsMessageReceiver implements Runnable {
    private final AmazonSQSClient sqs;
    private final String queueName;
    private boolean running;

    public SqsMessageReceiver(AmazonSQSClient sqs, String queueName) {
        this.sqs = sqs;
        this.queueName = queueName;
    }

    public void run() {
        running = true;
        while(running) {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueName).
                withMaxNumberOfMessages(10).withWaitTimeSeconds(10);
            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

            for (Message message : messages) {
                System.out.println("    Body:          " + message.getBody());
                // We must delete the message from the queue
                sqs.deleteMessage(new DeleteMessageRequest().withQueueUrl(queueName).withReceiptHandle(message.getReceiptHandle()));
            }

        }
    }

    public void stop() {
        running = false;
    }
}
