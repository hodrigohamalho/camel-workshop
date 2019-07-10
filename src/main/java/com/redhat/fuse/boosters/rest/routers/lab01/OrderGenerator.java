package com.redhat.fuse.boosters.rest.routers.lab01;

import com.redhat.fuse.boosters.rest.service.OrderService;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderGenerator extends RouteBuilder {

    @Override
    public void configure() throws Exception {

		// from("timer:generate?period=1000&repeatCount=5")
        //     .log("Generating Order...")
        //     .bean(OrderService.class, "generateOrder")
        //     .log("Order ${body.item} generated")
        // .to("direct:book-to-file");

		// from("direct:book-to-file")
		//     .choice()
        //         .when(simple("${body.item} == 'Camel'"))
        //             .log("Processing a camel book")
        //             .marshal().json()
        //             .to("file:/tmp/fuse-workshop/camel?fileName=camel-${date:now:yyyy-MM-dd-HHmmssSSS}.json")
        //         .otherwise()
        //             .log("Processing an activemq book")
        //             .process(new OrderProcessor())
        //             .marshal().jacksonxml()
        //             .to("file:/tmp/fuse-workshop/activemq?fileName=activemq-${date:now:yyyy-MM-dd-HHmmssSSS}.xml");

        // from("file:/tmp/fuse-workshop/activemq?delete=true")
        //     .log("uploading activemq orders to ftp")
        // .to("ftp://<ftp-username>@<ftp-host>?password=<ftp-password>&localWorkDirectory=<ftp-path>");

        // from("file:/tmp/fuse-workshop/camel?delete=true")
        //     .log("uploading camel orders to ftp")
        // .to("ftp://<ftp-username>@<ftp-host>?password=<ftp-password>&localWorkDirectory=<ftp-path>");
    }

}
