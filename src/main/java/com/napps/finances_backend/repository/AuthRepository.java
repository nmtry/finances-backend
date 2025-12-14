package com.napps.finances_backend.repository;

import com.napps.finances_backend.data.domain.UserInsertRequest;
import com.napps.finances_backend.data.model.User;
import com.napps.finances_backend.repository.mappers.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User insertUser(UserInsertRequest request) {
        String sql = """
                INSERT INTO NappsUser (
                    id,
                    full_name,
                    first_name,
                    last_name,
                    username,
                    email,
                    hashed_password,
                    avatar_url
                ) VALUES (
                    :id,
                    :fullName,
                    :firstName,
                    :lastName,
                    :username,
                    :email,
                    :hashedPassword,
                    :avatarUrl
                )
                RETURNING *
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", request.getId())
                .addValue("fullName", request.getFullName())
                .addValue("firstName", request.getFirstName())
                .addValue("lastName", request.getLastName())
                .addValue("username", request.getUsername())
                .addValue("email", request.getEmail())
                .addValue("hashedPassword", request.getHashedPassword())
                .addValue("avatarUrl", request.getAvatarUrl());

        return jdbcTemplate.queryForObject(sql, params, new UserRowMapper());
    }
}
