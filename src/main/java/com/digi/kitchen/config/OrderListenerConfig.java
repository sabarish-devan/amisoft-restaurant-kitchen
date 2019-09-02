package com.digi.kitchen.config;


import com.digi.kitchen.constant.RabbitmqConstant;
import com.digi.kitchen.eventListener.CreateOrderListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderListenerConfig {

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitmqConstant.ORDER_QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    CreateOrderListener receiver() {
        return new CreateOrderListener();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(CreateOrderListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveOrder");
    }
}
