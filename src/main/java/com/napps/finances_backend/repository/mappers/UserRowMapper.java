package com.napps.finances_backend.repository.mappers;

import com.napps.finances_backend.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setFullName(rs.getString("full_name"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setHashedPassword(rs.getString("hashed_password"));
        user.setAvatarUrl(rs.getString("avatar_url"));

        // timestamps
        user.setDateCreated(rs.getTimestamp("date_created").toInstant());
        user.setDateUpdated(rs.getTimestamp("date_updated").toInstant());
        user.setDateLastLogin(rs.getTimestamp("date_last_login").toInstant());

        return user;
    }
}
