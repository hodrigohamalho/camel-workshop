package com.redhat.fuse.boosters.rest.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersistenceRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Write your code here 
        // TIP: direct:getAllOrders

    }

    private String ds = "?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where " +
                                "id = :#${header.id}&" +
                                "outputClass=com.redhat.fuse.boosters.rest.model.Order" + ds;
}
 