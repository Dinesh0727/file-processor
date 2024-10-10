package com.tanla.ticket.file_processor.rabbitmq;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanla.ticket.file_processor.model.Message;
import com.tanla.ticket.file_processor.service.MessageService;

@Component
public class Receiver {
    // Didn't implement the latchDown here, because we work with only one thread and
    // there is no race condition
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private MessageService messageService;

    public void handleMessage(String messageReceived) throws InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(messageReceived, Message.class);
            System.out.println("Received Message : " + message.toString().replace("\n", "\\n") + "\n");
            messageService.addMessage(message);
        } catch (Exception e) {
            System.out.println("Error occurred while converting string to Message type");
        }

        Thread.sleep(100);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
