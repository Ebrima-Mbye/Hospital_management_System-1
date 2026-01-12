package com.group8.hospital_mangement_system.service;

import com.group8.hospital_mangement_system.dto.RegisterRequest;
import com.group8.hospital_mangement_system.reposotory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.group8.hospital_mangement_system.dto.LoginRequest;
import com.group8.hospital_mangement_system.dto.MessageResponse;
import com.group8.hospital_mangement_system.security.jwtService;
import com.group8.hospital_mangement_system.model.User;
import com.group8.hospital_mangement_system.dto.AuthResponse;
import java.util.Map;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private  jwtService jwtService;


    public MessageResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new MessageResponse("Email already in use");
        }

        User user = new User();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBirthDate(request.getBirthDate());
        user.setAddress(request.getAddress());
        userRepository.save(user);

        return new MessageResponse("Registered successfully");
    }


    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), Map.of());

        return new AuthResponse(token);
    }
}
