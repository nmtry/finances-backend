package com.napps.finances_backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserRef {
    private String id;
    private String fullName;
    private String username;
    private String email;
}
