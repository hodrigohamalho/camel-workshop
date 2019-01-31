package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.redhat.fuse.boosters.rest.model.Greetings;
import com.redhat.fuse.boosters.rest.model.Order;

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
                .to("direct:get-all-orders")
                .endRest()
        
            .post("/").type(Order.class).description("Create a new Order")
            	.route().routeId("insert-order").tracing()
            	.log("Order received")
            	.wireTap("direct:create-order")
				.setBody(constant("Your request was sent, thank you... Your confirmation will be sent by email."));

        rest("/greetings").description("Greeting to {name}")
            .get("/{name}").outType(Greetings.class)
                .route().routeId("greeting-api")
                .to("direct:greetingsImpl");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");     
    }

}