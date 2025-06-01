package com.github.tennyros.orderservice.integration;

import com.github.tennyros.orderservice.integration.fallback.UserServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallbackFactory = UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    String getUser(@PathVariable("id") Long id);

}
