package com.tanla.ticket.file_processor.Testing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tanla.ticket.file_processor.FileProcessorApplication;
import com.tanla.ticket.file_processor.rabbitmq.Receiver;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) {
        System.out.println("<--------- Sending Message -------->");
        rabbitTemplate.convertAndSend(FileProcessorApplication.topicExchangeName, "foo.bar.hello",
                "Hello this is a RabbitMQ message");
    }
}
