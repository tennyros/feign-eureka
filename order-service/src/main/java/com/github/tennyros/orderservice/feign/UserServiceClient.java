package com.github.tennyros.orderservice.feign;

import com.github.tennyros.orderservice.exception.UserNotFoundException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallbackFactory = UserServiceClient.UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    String getUser(@PathVariable("id") Long id);

    @Component
    class UserServiceClientFallbackFactory implements FallbackFactory<FallbackWithFactory> {
        @Override
        public FallbackWithFactory create(Throwable cause) {
            return new FallbackWithFactory(cause.getMessage());
        }
    }

    record FallbackWithFactory(String reason) implements UserServiceClient {
        @Override
        public String getUser(@PathVariable("id") Long id) {
            String responseMessage = """
                    User with id %s not found
                    """.formatted(id);
            throw new UserNotFoundException(responseMessage);
        }
    }

}
