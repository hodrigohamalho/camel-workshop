// package com.redhat.fuse.boosters.rest.routers;

// import org.apache.camel.builder.RouteBuilder;
// import org.springframework.stereotype.Component;

// @Component
// public class ContentBaseRouter extends RouteBuilder {

//     @Override
//     public void configure() throws Exception {
        
//         from("direct:cbr")
//             .choice()
//                 .when(header("paymentMethod").isEqualTo("credit"))
//                     .log("Processing credit payment...")
//                     .setBody(simple("[Credit Operation] Thanks for your purchased"))
//                 .when(header("paymentMethod").isEqualTo("cash"))
//                     .log("[Cash Operation] Processing debit payment...")
//                     .setBody(simple("[Cash Operation] Thanks for your purchased"))
//             .otherwise()
//                 .log("Payment error")
//                 .setBody(simple("Payment error, try again choosing paymentMethod credit or cash"));
        
//     }

// }