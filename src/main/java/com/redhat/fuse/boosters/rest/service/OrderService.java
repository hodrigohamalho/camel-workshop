package com.redhat.fuse.boosters.rest.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.redhat.fuse.boosters.rest.model.Order;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private final AtomicInteger counter = new AtomicInteger();

    private final Random amount = new Random();
    
    public String createOrder(Order order){
		return "order: "+order.getId();
    }

    public Order generateOrder() {
        Order order = new Order();
        order.setId(counter.incrementAndGet());
        order.setItem(counter.get() % 2 == 0 ? "Camel" : "ActiveMQ");
        order.setAmount(amount.nextInt(10) + 1);
        order.setDescription(counter.get() % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
        return order;
    }

    public Order rowToOrder(Map<String, Object> row) {
        Order order = new Order();
        order.setId((Integer) row.get("id"));
        order.setItem((String) row.get("item"));
        order.setAmount((Integer) row.get("amount"));
        order.setDescription((String) row.get("description"));
        order.setProcessed((Boolean) row.get("processed"));
        return order;
    }
}