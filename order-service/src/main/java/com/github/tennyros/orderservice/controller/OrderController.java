package com.github.tennyros.orderservice.controller;

import com.github.tennyros.orderservice.integration.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final UserServiceClient userServiceClient;

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Order #%d belongs to: %s", id, userServiceClient.getUser(id)));
    }

}
