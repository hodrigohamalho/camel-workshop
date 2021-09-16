package com.redhat.fuse.boosters.rest.routers;

import com.redhat.fuse.boosters.rest.model.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Order order = exchange.getIn().getBody(Order.class);

        order.setProcessed(true);
        System.out.println("attributed process changed");


        exchange.getOut().setBody(order);
	}

}
