package com.ecomapi.ws.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserLoginRequest {
    private String username;
    private String password;
}
