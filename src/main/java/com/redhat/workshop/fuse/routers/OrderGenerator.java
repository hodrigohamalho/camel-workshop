package com.redhat.workshop.fuse.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import com.redhat.workshop.fuse.service.OrderService;

@Component
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:generate")
            .log("hello router!");

    }

}
