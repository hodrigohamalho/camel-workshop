package com.redhat.fuse.boosters.rest.routers.lab02;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.redhat.fuse.boosters.rest.model.Order;

@Component
public class RestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/orders").description("Orders CRUD REST endpoint")
                .get("/").description("Get all orders")
                .route().routeId("all-orders")
                .log("Getting all order entries from database")
                .to(this.selectAll)
                .endRest()

                .get("/{id}").description("Get orders by id")
                .route().routeId("find-by-id")
                .log("Getting order with id ${header.id} entry from database")
                .to(this.selectById)
                .endRest()
                .post("/async").type(Order.class).description("Create a new order")
                .route().routeId("create-order-async")
                .log("Order received")
                .wireTap("direct:create-order")
                .setBody().simple("We received your request. As soon we process your request we will notify you by email.")
                .endRest();

        from("direct:create-order")
                .log("sending ${body.item} to JMS queue")
                .to("jms:queue:orders");

// Consume from the message broker queue
        from("jms:queue:orders")
                .log("received ${body.item} from JMS queue")
                .to(this.insertOrder)
                .to("mock:notify-by-email");
 
    }

    // Query support
    private String ds = "?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where id = :#${header.id}"  + ds;
    private String insertOrder = "sql:insert into orders (item, amount, description, processed) values " +
    	                		"(:#${body.item}, :#${body.amount}, :#${body.description}, false)"+ ds;

}