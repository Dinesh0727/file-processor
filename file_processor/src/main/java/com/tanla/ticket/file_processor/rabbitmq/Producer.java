package com.tanla.ticket.file_processor.rabbitmq;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanla.ticket.file_processor.FileProcessorApplication;

@Component
public class Producer {

    private RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessages(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            rabbitTemplate.convertAndSend(FileProcessorApplication.topicExchangeName, "foo.bar.hello", strings.get(i));
        }
    }
}
