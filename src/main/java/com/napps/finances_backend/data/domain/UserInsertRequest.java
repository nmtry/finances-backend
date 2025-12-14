package com.napps.finances_backend.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserInsertRequest {
    private String id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String hashedPassword;
    private String avatarUrl;
}
