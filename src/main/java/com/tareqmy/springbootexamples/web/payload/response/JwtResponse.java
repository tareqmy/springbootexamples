package com.tareqmy.springbootexamples.web.payload.response;

import java.util.List;

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

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
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
