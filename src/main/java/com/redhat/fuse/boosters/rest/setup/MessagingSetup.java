package com.redhat.fuse.boosters.rest.setup;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingSetup {
	
	@Value("${activemq.broker.url}")
	private String brokerUrl;

	@Value("${activemq.broker.username}")
	private String userName;

	@Value("${activemq.broker.password}")
	private String password;

	@Bean(name = "activemq")
    public ActiveMQComponent createComponent(ActiveMQConnectionFactory factory) {
		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		
		factory.setBrokerURL(brokerUrl);
		factory.setUserName(userName);
		factory.setPassword(password);

		factory.setTrustAllPackages(true);
        activeMQComponent.setConnectionFactory(factory);
        return activeMQComponent;
    }
	
}