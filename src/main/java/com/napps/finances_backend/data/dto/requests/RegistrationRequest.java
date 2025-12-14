package com.napps.finances_backend.data.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationRequest {
    private String fullName;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
}
