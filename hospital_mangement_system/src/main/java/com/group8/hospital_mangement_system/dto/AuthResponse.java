package com.group8.hospital_mangement_system.dto;

public class AuthResponse {
    private  String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
