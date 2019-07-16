package com.redhat.fuse.boosters.rest.routers.lab02;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.redhat.fuse.boosters.rest.model.Order;

@Component
public class RestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // PUT YOUR CODE HERE
 
    }

    // Query support
    private String ds = "?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where id = :#${header.id}"  + ds;
    private String insertOrder = "sql:insert into orders (item, amount, description, processed) values " +
    	                		"(:#${body.item}, :#${body.amount}, :#${body.description}, false)"+ ds;

}