package com.massafra.club.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.massafra.club.constants.PersistRabbitMq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class RabbitMQConfig  {

    private static final String XDLE = "x-dead-letter-exchange";
    private static final String XDLRK = "x-dead-letter-routing-key";

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public DirectExchange protheusExchange() {
        return new DirectExchange(PersistRabbitMq.EXCHANGE_PROTHEUS_COMMANDS);
    }

    @Bean
    public DirectExchange protheusDeadExchange() {
        return new DirectExchange(PersistRabbitMq.EXCHANGE_DEAD_PROTHEUS_COMMANDS);
    }

    @Bean
    public Queue orderCreateQueue() {
        return QueueBuilder.durable(PersistRabbitMq.ORDER_CREATE_QUEUE)
                .withArgument(XDLE, PersistRabbitMq.EXCHANGE_DEAD_PROTHEUS_COMMANDS)
                .withArgument(XDLRK, PersistRabbitMq.CREATE_ORDER_DEAD_ROUTING_KEY)
                .ttl(10000)
                .build();
    }

    @Bean
    public Queue orderCreateQueueDead() {
        return QueueBuilder.durable(PersistRabbitMq.ORDER_CREATE_QUEUE_DEAD).build();
    }

    @Bean
    public Declarables bindings() {
        return new Declarables(
                orderCreateBinding(),
                orderCreateDeadBinding()
        );
    }

    @Bean
    public Binding orderCreateBinding() {
        return new Binding(PersistRabbitMq.ORDER_CREATE_QUEUE, Binding.DestinationType.QUEUE, PersistRabbitMq.EXCHANGE_PROTHEUS_COMMANDS,
                PersistRabbitMq.CREATE_ORDER_ROUTING_KEY, null);
    }

    @Bean
    public Binding orderCreateDeadBinding() {
        return new Binding(PersistRabbitMq.ORDER_CREATE_QUEUE_DEAD, Binding.DestinationType.QUEUE, PersistRabbitMq.EXCHANGE_DEAD_PROTHEUS_COMMANDS,
                PersistRabbitMq.CREATE_ORDER_DEAD_ROUTING_KEY, null);
    }


}
