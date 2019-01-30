package com.redhat.fuse.boosters.rest.service;

import com.redhat.fuse.boosters.rest.model.Greetings;

import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component
public class GreetingsService{

    private static final String THE_GREETINGS = "Hello, ";

    public Greetings getGreetings(@Header("name") String name ) {
        return new Greetings( THE_GREETINGS + name );
    }

}