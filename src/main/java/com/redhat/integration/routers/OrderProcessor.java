package com.redhat.integration.routers;

import javax.enterprise.context.ApplicationScoped;
import com.redhat.integration.model.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class OrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        //insert logic here
	}

}
