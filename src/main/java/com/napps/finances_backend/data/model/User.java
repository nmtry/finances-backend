package com.napps.finances_backend.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    private String id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String hashedPassword;
    private String avatarUrl;
    private Instant dateCreated;
    private Instant dateUpdated;
    private Instant dateLastLogin;
}
