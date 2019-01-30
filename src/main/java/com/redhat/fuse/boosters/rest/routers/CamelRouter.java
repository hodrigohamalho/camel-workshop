package com.redhat.fuse.boosters.rest.routers;

import com.redhat.fuse.boosters.rest.model.Greetings;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/orders").description("Content Base Router Example")

            .get("/pay") // yes, it should be post... but let's thing more didact here :)
                .route().routeId("content-base-router")
                .to("direct:cbr")
                .endRest()

            .get("/")
                .route().routeId("get-all-orders")
                .to("direct:getAllOrders");

        rest("/greetings").description("Greeting to {name}")
            .get("/{name}").outType(Greetings.class)
                .route().routeId("greeting-api")
                .to("direct:greetingsImpl");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");     
    }

}