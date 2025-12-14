package com.napps.finances_backend.data.dto.responses;

import com.napps.finances_backend.data.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationResponse {
    private UserDTO data;
}
