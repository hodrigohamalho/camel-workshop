package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

import com.redhat.fuse.boosters.rest.service.OrderService;

@Component
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:generate?repeatCount=1")
            .log("hello router!");

    }

}
