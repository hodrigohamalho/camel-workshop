package com.redhat.workshop.fuse.setup;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestSetup extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        restConfiguration()
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Greeting REST API")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true")
            .apiProperty("base.path", "camel/")
            .apiProperty("api.path", "/")
            .apiProperty("host", "")
            .apiProperty("schemes", "https,http")
            .apiContextRouteId("doc-api")
        .component("servlet")
        .bindingMode(RestBindingMode.json);
    
    }
}
