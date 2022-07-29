package com.redhat.integration.routers;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class OrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        //insert logic here
	}

}
