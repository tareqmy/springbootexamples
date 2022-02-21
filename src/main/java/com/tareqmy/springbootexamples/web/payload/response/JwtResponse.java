package com.tareqmy.springbootexamples.web.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private String username;

    private final List<String> roles;

    public JwtResponse(String accessToken, String username, List<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
            "token='" + token + '\'' +
            ", type='" + type + '\'' +
            ", username='" + username + '\'' +
            ", roles=" + roles +
            '}';
    }
}
