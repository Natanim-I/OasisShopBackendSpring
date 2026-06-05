package com.oasis.OasisShop.service;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtService {

    public String generateToken(String username) {
        return "";
    }

    public String extractUsername(String token) {
        return "";
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }
}
