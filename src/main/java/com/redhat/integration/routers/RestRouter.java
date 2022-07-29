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
    	rest("/orders")
    	
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
    	
	    	.post("/").type(Order.class).description("Create a new order")
		        .route().routeId("create order")
		        .log("Order received")
		        .to(this.insertOrder)
		        .setBody().simple("{\"status\":\"Order received!\"}")
		        .endRest()
    	
    	
		    .post("/async").type(Order.class).description("Create a new order")
		        .route().routeId("create-order-async")
		        .log("Order received")
		        .wireTap("direct:create-order")
		        .setBody().simple("{\"status\":\"We received your request. It will be process as soon as posible!.\"}")
		        .endRest();
		
    	from("direct:create-order")
	        .log("sending ${body.item} to JMS queue")
	        .to("jms:queue:orders");
    	
    	// Consume from the message broker queue
    	from("jms:queue:orders")
    	    .log("received id: ${body.id} item: ${body.item} from JMS queue")
    	    .to(this.insertOrder);
    	    //.to("mock:notify-by-email");
    	
    }

    // Query support
    private String ds = "";//"?dataSource=dataSource";
    private String selectAll = "sql:select * from orders" + ds;
    private String selectById = "sql:select * from orders where id = :#${header.id}"  + ds;
    private String insertOrder = "sql:insert into orders (item, amount, description, processed) values " +
    	                		"(:#${body.item}, :#${body.amount}, :#${body.description}, false)"+ ds;

}
