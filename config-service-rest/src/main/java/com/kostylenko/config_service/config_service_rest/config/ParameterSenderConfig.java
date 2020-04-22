package com.kostylenko.config_service.config_service_rest.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ParameterSenderConfig {

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.broker.username}")
    private String brokerUsername;

    @Value("${jms.broker.password}")
    private String brokerPassword;

    @Value("${jms.activemq.topic-name}")
    private String topicName;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(brokerUsername);
        connectionFactory.setPassword(brokerPassword);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestination(new ActiveMQTopic(topicName));
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        return jmsTemplate;
    }
}
