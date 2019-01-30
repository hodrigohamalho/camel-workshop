package com.redhat.fuse.boosters.rest.routers;

import com.redhat.fuse.boosters.rest.service.OrderService;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from("timer:generate?period=1000&repeatCount=2")
            .log("Generating Order...")
            .bean(OrderService.class, "generateOrder")
            .log("Order ${body.item} generated")
            .to("direct:sendBook");

        from("direct:sendBook")
            .choice()
                .when(simple("${body.item} == 'Camel'"))
                    .log("Processing Camel book")
                    .to("mock:camel-store")
                .when(simple("${body.item} == 'ActiveMQ'"))
                    .log("Processing ActiveMQ book")                
                    .to("mock:activemq-store");
        
    }

}