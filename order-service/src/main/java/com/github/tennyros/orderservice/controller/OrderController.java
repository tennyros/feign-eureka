package com.github.tennyros.orderservice.controller;

import com.github.tennyros.orderservice.feign.UserServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final UserServiceClient userServiceClient;

    public OrderController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        return String.format("Order #%d belongs to: %s", id, userServiceClient.getUser(id));
    }

}
