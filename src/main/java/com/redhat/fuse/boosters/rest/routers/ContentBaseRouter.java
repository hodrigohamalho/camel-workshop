package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentBaseRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from("direct:cbr")
            .log("foo")
            // start your code here...
            // tip: .when(header("paymentMethod").isEqualTo("credit"))
        ;
        
    }

}