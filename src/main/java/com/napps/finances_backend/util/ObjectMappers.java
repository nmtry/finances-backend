package com.napps.finances_backend.util;

import com.napps.finances_backend.data.dto.AccountDTO;
import com.napps.finances_backend.data.model.Account;

public final class ObjectMappers {
    private ObjectMappers() {}

    public static AccountDTO accountToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getType(),
                account.getName(),
                account.getInstitution(),
                account.getCreditLimit(),
                account.getImageUrl(),
                account.getPartitions(),
                account.getOwnerships()
        );
    }
}
