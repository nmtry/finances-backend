package com.napps.finances_backend.service;

import com.napps.finances_backend.data.domain.UserInsertRequest;
import com.napps.finances_backend.data.dto.requests.RegistrationRequest;
import com.napps.finances_backend.data.dto.responses.RegistrationResponse;
import com.napps.finances_backend.data.model.User;
import com.napps.finances_backend.repository.AuthRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public ResponseEntity<RegistrationResponse> registerUser(RegistrationRequest request) {
        String newId = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        UserInsertRequest userInsertRequest = new UserInsertRequest(
                newId,
                request.getFullName(),
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                hashedPassword,
                request.getAvatarUrl()
        );

        User newUser = authRepository.insertUser(userInsertRequest);

        return ResponseEntity.ok(new RegistrationResponse());
    }
}
