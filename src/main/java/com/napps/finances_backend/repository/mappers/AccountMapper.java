package com.napps.finances_backend.repository.mappers;

import com.napps.finances_backend.data.dto.AccountOwnershipRef;
import com.napps.finances_backend.data.dto.AccountPartitionRef;
import com.napps.finances_backend.data.dto.UserRef;
import com.napps.finances_backend.data.enums.AccountOwnershipType;
import com.napps.finances_backend.data.enums.AccountPartitionType;
import com.napps.finances_backend.data.enums.AccountType;
import com.napps.finances_backend.data.model.Account;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMapper implements ResultSetExtractor<List<Account>> {
    @Override
    public List<Account> extractData(ResultSet rs) throws SQLException {
        Map<String, Account> accountMap = new HashMap<>();

        while(rs.next()) {
            String accountId = rs.getString("a_id");
            Account account = accountMap.get(accountId);

            if(account == null) {
                account = new Account();
                account.setId(accountId);
                account.setName(rs.getString("a_name"));
                account.setType(AccountType.valueOf(rs.getString("a_type")));
                account.setCreditLimit(rs.getLong("a_credit_limit"));
                account.setInstitution(rs.getString("a_institution"));
                account.setImageUrl(rs.getString("a_image_url"));
                account.setDateCreated(rs.getTimestamp("a_date_created").toInstant());
                account.setDateUpdated(rs.getTimestamp("a_date_updated").toInstant());
                accountMap.put(accountId, account);
            }

            String partitionId = rs.getString("p_id");
            if(partitionId != null && !partitionId.isEmpty()) { // maybe != "" or something?
                AccountPartitionRef partitionRef = new AccountPartitionRef();
                partitionRef.setId(partitionId);
                partitionRef.setParentAccountId(rs.getString("p_parent_account_id"));
                partitionRef.setType(AccountPartitionType.valueOf(rs.getString("p_type")));
                partitionRef.setName(rs.getString("p_name"));
                partitionRef.setBalance(rs.getLong("p_balance"));
                account.getPartitions().add(partitionRef);
            }

            String ownershipId = rs.getString("o_id");
            if(ownershipId != null && !ownershipId.isEmpty()) {
                AccountOwnershipRef ownershipRef = new AccountOwnershipRef();
                UserRef userRef = new UserRef();
                userRef.setId(rs.getString("o_user_id"));
                userRef.setEmail(rs.getString("u_email"));
                userRef.setUsername(rs.getString("u_username"));
                userRef.setFullName(rs.getString("u_full_name"));
                ownershipRef.setId(ownershipId);
                ownershipRef.setAccountId(rs.getString("o_account_id"));
                ownershipRef.setType(AccountOwnershipType.valueOf(rs.getString("o_type")));
                ownershipRef.setUser(userRef);
                account.getOwnerships().add(ownershipRef);
            }
        }

        return new ArrayList<>(accountMap.values());
    }
}
