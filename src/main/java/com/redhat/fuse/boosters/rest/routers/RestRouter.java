package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.redhat.fuse.boosters.rest.model.Order;

@Component
public class RestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/orders").description("Order rest lab")

            .get("/")
                .route().routeId("get-all-orders")
                .log("geting all orders entries from database")
                .to(this.selectAll)
                .endRest()

            .get("/{id}")
                .route().routeId("get-order-by-id")
                .log("geting order with id ${header.id} entry from database")
                .to(this.selectById)
                .endRest()
        
            .post("/").type(Order.class).description("Create a new Order")
            	.route().routeId("insert-order").tracing()
            	.log("Order received")
            	.wireTap("direct:create-order")
				.setBody(constant("Your request was sent, thank you... Your confirmation will be sent by email."));
 
    }

    // Query support
    private String ds = "?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where id = :#${header.id}"  + ds;

}