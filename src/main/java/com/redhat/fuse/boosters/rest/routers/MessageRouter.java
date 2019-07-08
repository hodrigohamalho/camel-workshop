// package com.redhat.fuse.boosters.rest.routers;

// import org.apache.camel.Exchange;
// import org.apache.camel.ExchangePattern;
// import org.apache.camel.Processor;
// import org.apache.camel.builder.RouteBuilder;
// import org.springframework.stereotype.Component;

// @Component
// public class MessageRouter extends RouteBuilder{

// 	@Override
// 	public void configure() throws Exception {

// 		from("direct:create-order")
// 			.log("Sending Order to AMQ broker: ${body}")
// 			.to(ExchangePattern.InOnly, "activemq:queue:backend");

// 		// Place your code here...
// 		from("activemq:queue:backend")
// 			.log("Backend logic...")
// 		.to("activemq:queue:notify");

// 		from("activemq:queue:notify")
// 			.log("Sending email...")
// 		.to("mock:send-email");
			
// 	}

// }
