package com.github.tennyros.orderservice.integration.fallback;

import com.github.tennyros.orderservice.exception.ExternalServiceCommunicationException;
import com.github.tennyros.orderservice.exception.ExternalServiceUnavailableException;
import com.github.tennyros.orderservice.exception.UserNotFoundException;
import com.github.tennyros.orderservice.integration.UserServiceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserServiceClientFallback implements UserServiceClient {

    private final Throwable cause;

    @Override
    public String getUser(Long id) {
        log.error("Fallback triggered due to: {}", cause.getMessage(), cause);

        String causeMessage = cause.getMessage() != null ? cause.getMessage() : "";

        if (cause instanceof FeignException.NotFound) {
            throw new UserNotFoundException("User with id %d not found".formatted(id));
        }

        if (cause instanceof FeignException.ServiceUnavailable ||
            cause instanceof FeignException.BadGateway ||
            cause instanceof FeignException.GatewayTimeout ||
            cause instanceof IllegalStateException ||
            causeMessage.contains("No servers available")) {
            throw new ExternalServiceUnavailableException("User-service is currently unavailable");
        }

        throw new ExternalServiceCommunicationException("Failed to call user-service", cause);
    }
}
