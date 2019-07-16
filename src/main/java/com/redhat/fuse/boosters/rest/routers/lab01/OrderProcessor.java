package com.redhat.fuse.boosters.rest.routers.lab01;

import com.redhat.fuse.boosters.rest.model.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Order order = exchange.getIn().getBody(Order.class);
        
        // set order process to true here

        
        exchange.getOut().setBody(order);
	}

}