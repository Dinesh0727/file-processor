package com.tanla.ticket.file_processor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tanla.ticket.file_processor.rabbitmq.Receiver;

@SpringBootApplication
public class FileProcessorApplication {

	public static final String topicExchangeName = "spring-boot-exchange";

	public static final String queueName = "spring-boot";

	public static void main(String[] args) {
		SpringApplication.run(FileProcessorApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver);
	}

}
