package com.napps.finances_backend.repository;

import com.napps.finances_backend.data.model.Account;
import com.napps.finances_backend.repository.mappers.AccountMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add aliases for all (a_id for a.id)
    public List<Account> getAccounts() {
        String sql = """
                SELECT a.id AS a_id,
                       a.type AS a_type,
                       a.name AS a_name,
                       a.institution AS a_institution,
                       a.credit_limit AS a_credit_limit,
                       a.image_url AS a_image_url,
                       a.date_created AS a_date_created,
                       a.date_updated AS a_date_updated,
            
                       p.id AS p_id,
                       p.parent_account_id AS p_parent_account_id,
                       p.type AS p_type,
                       p.name AS p_name,
                       p.balance AS p_balance,
            
                       o.id AS o_id,
                       o.account_id AS o_account_id,
                       o.type AS o_type,
                       o.user_id AS o_user_id,
            
                       u.id AS u_id,
                       u.full_name AS u_full_name,
                       u.email AS u_email,
                       u.username AS u_username
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
