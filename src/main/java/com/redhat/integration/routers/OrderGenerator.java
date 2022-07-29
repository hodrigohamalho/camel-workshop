package com.redhat.integration.routers;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import com.redhat.integration.service.OrderService;

@ApplicationScoped
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

    	from("timer:generate?repeatCount=5&period=1000")
	        .log("Generating Order...")
	        .bean(OrderService.class, "generateOrder")
	        .log("Order ${body.item} generated")
        .to("direct:book-to-file");
    	
    	from("direct:book-to-file")
        .choice()
            .when(simple("${body.item} == 'Camel'"))
                .log("Processing a camel book")
                .marshal().json()
                .to("file:/tmp/camel-workshop/camel?fileName=camel-${date:now:yyyy-MM-dd-HHmmssSSS}.json")
            .otherwise()
                .log("Processing an activemq book")
                .process(new OrderProcessor()) // ADD THIS LINE
                .marshal().jacksonxml()
                .to("file:/tmp/camel-workshop/activemq?fileName=activemq-${date:now:yyyy-MM-dd-HHmmssSSS}.xml");

    }

}
