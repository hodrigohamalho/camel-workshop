package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("direct:create-order")
			.log("Sending Order to AMQ broker: ${body}")
			.to(ExchangePattern.InOnly, "activemq:queue:backend");

		// Place your code here...
		from("activemq:queue:backend")
			.log("Continue your code here...");
		
	}

}
