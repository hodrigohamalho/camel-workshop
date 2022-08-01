package com.redhat.integration.routers;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import com.redhat.integration.service.OrderService;

@ApplicationScoped
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

    	from("timer:generate")
    		.log("Hello router!");

    }

}
