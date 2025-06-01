package com.github.tennyros.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Map<Long, String> users = Map.of(
            1L, "Ivan",
            2L, "Peter",
            3L, "Sasha"
    );

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        if (!users.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(users.get(id));
    }
}
