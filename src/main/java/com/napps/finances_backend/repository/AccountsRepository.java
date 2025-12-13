package com.napps.finances_backend.repository;

import com.napps.finances_backend.data.model.Account;
import com.napps.finances_backend.repository.mappers.AccountMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AccountsRepository {
    private final JdbcTemplate jdbcTemplate;

    public AccountsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add aliases for all (a_id for a.id)
    public List<Account> getAccounts() {
        String sql = """
            SELECT a.id, a.type, a.name, a.institution, a.credit_limit, a.image_url, a.date_created, a.date_updated,

                   p.id, p.parent_account_id, p.type, p.name, p.balance,

                   o.id, o.account_id, o.type, o.user_id, u.full_name, u.email, u.username
            FROM Account a
            LEFT JOIN AccountPartition p ON a.id = p.parent_account_id
            LEFT JOIN AccountOwnership o ON a.id = o.account_id
            LEFT JOIN NappsUser u ON o.user_id = u.id
            """;

        return jdbcTemplate.query(sql, rs -> {
            new AccountMapper();
            return new AccountMapper().extractData(rs);
        });
    }
}
