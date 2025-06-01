package com.github.tennyros.orderservice.integration.fallback;

import com.github.tennyros.orderservice.integration.UserServiceClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable cause) {
        return new UserServiceClientFallback(cause);
    }

}
