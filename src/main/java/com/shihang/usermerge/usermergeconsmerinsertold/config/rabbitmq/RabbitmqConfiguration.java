package com.shihang.usermerge.usermergeconsmerinsertold.config.rabbitmq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author jiangzhuangnai
 * @date 2021/3/15
 * @since 1.0.0
 **/
@Configuration
public class RabbitmqConfiguration {

    @Bean(name = "tmpConnectionFactory")
    @Primary
    public ConnectionFactory tmpConnectionFactory(
            @Value("${spring.rabbitmq.tmp.host}") String host,
            @Value("${spring.rabbitmq.tmp.port}") int port,
            @Value("${spring.rabbitmq.tmp.username}") String username,
            @Value("${spring.rabbitmq.tmp.password}") String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "tmpRabbitTemplate")
    @Primary
    public RabbitTemplate tmpRabbitTemplate(
            @Qualifier("tmpConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }

    @Bean(name = "tmpContainerFactory")
    public SimpleRabbitListenerContainerFactory normalFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("tmpConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}