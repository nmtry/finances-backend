-- ==========================
-- User Table
-- ==========================
CREATE TABLE NappsUser (
    id UUID DEFAULT RANDOM_UUID(),
    CONSTRAINT pk_nappsuser PRIMARY KEY (id),
    full_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_user_username ON NappsUser(username);
CREATE INDEX idx_user_email ON NappsUser(email);

-- ==========================
-- Account Table
-- ==========================
CREATE TABLE Account (
    id UUID DEFAULT RANDOM_UUID(),
    CONSTRAINT pk_account PRIMARY KEY (id),
    type VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    institution VARCHAR(255) NOT NULL,
    credit_limit INTEGER,
    image_url VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_account_type ON Account(type);
CREATE INDEX idx_account_institution ON Account(institution);

-- ==========================
-- AccountPartition Table
-- ==========================
CREATE TABLE AccountPartition (
    id UUID DEFAULT RANDOM_UUID(),
    CONSTRAINT pk_accountpartition PRIMARY KEY (id),
    parent_account_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    balance INTEGER NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_accountpartition_account FOREIGN KEY (parent_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX idx_accountpartition_parent_account_id ON AccountPartition(parent_account_id);
CREATE UNIQUE INDEX uq_accountpartition_parent_name ON AccountPartition(parent_account_id, name);

-- ==========================
-- AccountOwnership Table
-- ==========================
CREATE TABLE AccountOwnership (
    id UUID DEFAULT RANDOM_UUID(),
    CONSTRAINT pk_accountownership PRIMARY KEY (id),
    user_id UUID NOT NULL,
    account_id UUID NOT NULL,
    ownership_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_accountownership_user FOREIGN KEY (user_id)
        REFERENCES NappsUser(id) ON DELETE CASCADE,
    CONSTRAINT fk_accountownership_account FOREIGN KEY (account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX idx_accountownership_user_id ON AccountOwnership(user_id);
CREATE INDEX idx_accountownership_account_id ON AccountOwnership(account_id);
CREATE INDEX idx_accountownership_type ON AccountOwnership(ownership_type);
CREATE UNIQUE INDEX uq_accountownership_user_account ON AccountOwnership(user_id, account_id);

-- ==========================
-- Transaction Table
-- ==========================
CREATE TABLE FinancialTransaction (
    id UUID DEFAULT RANDOM_UUID(),
    CONSTRAINT pk_financialtransaction PRIMARY KEY (id),
    user_id UUID NOT NULL,
    type VARCHAR(50) NOT NULL,
    from_account_id UUID,
    to_account_id UUID,
    from_name VARCHAR(255),
    to_name VARCHAR(255),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id)
        REFERENCES NappsUser(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_from_account FOREIGN KEY (from_account_id)
        REFERENCES Account(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_to_account FOREIGN KEY (to_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX idx_transaction_user_id ON FinancialTransaction(user_id);
CREATE INDEX idx_transaction_type ON FinancialTransaction(type);
CREATE INDEX idx_transaction_from_account_id ON FinancialTransaction(from_account_id);
CREATE INDEX idx_transaction_to_account_id ON FinancialTransaction(to_account_id);
CREATE INDEX idx_transaction_date ON FinancialTransaction(transaction_date);
