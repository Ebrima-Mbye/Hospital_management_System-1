package com.group8.hospital_mangement_system.controller;
import com.group8.hospital_mangement_system.dto.AuthResponse;
import com.group8.hospital_mangement_system.dto.LoginRequest;
import com.group8.hospital_mangement_system.dto.MessageResponse;
import com.group8.hospital_mangement_system.dto.RegisterRequest;
import com.group8.hospital_mangement_system.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
