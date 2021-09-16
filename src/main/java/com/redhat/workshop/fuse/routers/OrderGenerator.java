package com.redhat.workshop.fuse.routers;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

@Component
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:generate?repeatCount=1")
            .log("hello router!");

    }

}
