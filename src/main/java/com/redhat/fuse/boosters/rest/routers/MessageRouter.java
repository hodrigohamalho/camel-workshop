package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		// mock a order creation
		from("direct:create-order")
			.log("Sending Order to AMQ broker: ${body}")
			.to(ExchangePattern.InOnly, "activemq:queue:backend");

		from("activemq:queue:backend")
			.log("Message received from the AMQ broker: ${body}")
			.log("Sending message to send-email queue")
			.to(ExchangePattern.InOnly, "activemq:queue:send-email");

		from("activemq:queue:send-email")
			.log("Sending email...")
			.to("mock:smtp");
		
	}

}
