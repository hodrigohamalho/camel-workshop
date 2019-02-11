package com.redhat.fuse.boosters.rest.routers;

import com.redhat.fuse.boosters.rest.service.OrderService;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderGeneratorRouter extends RouteBuilder {

    
    @Override
    public void configure() throws Exception {
        
        from("timer:generate")
            .log("Hello router!");
        
    }

}