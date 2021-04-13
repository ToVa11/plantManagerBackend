package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    @Autowired
    JWTTokenProvider jwtTokenProvider;

    public String getUsernameFromToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        return jwtTokenProvider.getSubject(token);
    }
}
