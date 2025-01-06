package com.academico.webproject.controller;

import com.academico.webproject.dto.UserLoginRequest;
import com.academico.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            String userId = userService.getUserIdByEmail(loginRequest.getEmail());

            // Returns a JSON with the user ID and other relevant information
            return ResponseEntity.ok(Map.of(
                    "id", userId,
                    "message", "Login successful!"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Invalid email or password."
            ));
        }
    }
}
