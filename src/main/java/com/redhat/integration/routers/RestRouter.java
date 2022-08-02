package com.redhat.integration.routers;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.redhat.integration.model.Order;

@ApplicationScoped
public class RestRouter extends RouteBuilder {

@Override
public void configure() throws Exception {
    	
    restConfiguration()
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "Orders REST API")
        .apiProperty("api.version", "1.0")
        .apiProperty("cors", "true")
        .apiProperty("base.path", "camel/")
        .apiProperty("api.path", "/")
        .apiProperty("host", "")
        .apiProperty("schemes", "https,http")
        .apiContextRouteId("doc-api")
    .bindingMode(RestBindingMode.json);

    // insert your rest code here
    	
    	
    }

    // Query support
    private String ds = "";//"?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where id = :#${header.id}"  + ds;
    private String insertOrder = "sql:insert into orders (item, amount, description, processed) values " +
    	                		"(:#${body.item}, :#${body.amount}, :#${body.description}, false)"+ ds;

}
