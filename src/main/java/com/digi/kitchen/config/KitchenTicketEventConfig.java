package com.digi.kitchen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.digi.kitchen.constant.RabbitmqConstant.KITCHEN_EXCHANGE_NAME;
import static com.digi.kitchen.constant.RabbitmqConstant.KITCHEN_QUEUE_NAME;

@Configuration
public class KitchenTicketEventConfig {

    @Bean
    Queue queue() {
        return new Queue(KITCHEN_QUEUE_NAME, true);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(KITCHEN_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        template.setMessageConverter(jsonConverter);
        return template;
    }

}
