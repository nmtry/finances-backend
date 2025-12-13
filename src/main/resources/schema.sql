-- ==========================
-- User Table
-- ==========================
CREATE TABLE IF NOT EXISTS NappsUser (
    id TEXT NOT NULL,
    CONSTRAINT pk_nappsuser PRIMARY KEY (id),
    full_name TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    username TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    hashed_password TEXT NOT NULL,
    avatar_url TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_user_username ON NappsUser(username);
CREATE INDEX IF NOT EXISTS idx_user_email ON NappsUser(email);

-- ==========================
-- Account Table
-- ==========================
CREATE TABLE IF NOT EXISTS Account (
    id TEXT NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id),
    type TEXT NOT NULL,
    name TEXT NOT NULL,
    institution TEXT NOT NULL,
    credit_limit INTEGER,
    image_url TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_account_type ON Account(type);
CREATE INDEX IF NOT EXISTS idx_account_institution ON Account(institution);

-- ==========================
-- AccountPartition Table
-- ==========================
CREATE TABLE IF NOT EXISTS AccountPartition (
    id TEXT NOT NULL,
    CONSTRAINT pk_accountpartition PRIMARY KEY (id),
    parent_account_id TEXT NOT NULL,
    type TEXT NOT NULL, -- "main", which is not deletable, or "custom", which is --
    name TEXT NOT NULL,
    balance INTEGER NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_accountpartition_account FOREIGN KEY (parent_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_accountpartition_parent_account_id ON AccountPartition(parent_account_id);
CREATE UNIQUE INDEX IF NOT EXISTS uq_accountpartition_parent_name ON AccountPartition(parent_account_id, name);

-- ==========================
-- AccountOwnership Table
-- ==========================
CREATE TABLE IF NOT EXISTS AccountOwnership (
    id TEXT NOT NULL,
    CONSTRAINT pk_accountownership PRIMARY KEY (id),
    user_id TEXT NOT NULL,
    account_id TEXT NOT NULL,
    type TEXT NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_accountownership_user FOREIGN KEY (user_id)
        REFERENCES NappsUser(id) ON DELETE CASCADE,
    CONSTRAINT fk_accountownership_account FOREIGN KEY (account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_accountownership_user_id ON AccountOwnership(user_id);
CREATE INDEX IF NOT EXISTS idx_accountownership_account_id ON AccountOwnership(account_id);
CREATE INDEX IF NOT EXISTS idx_accountownership_type ON AccountOwnership(ownership_type);
CREATE UNIQUE INDEX IF NOT EXISTS uq_accountownership_user_account ON AccountOwnership(user_id, account_id);

-- ==========================
-- Transaction Table
-- ==========================
CREATE TABLE IF NOT EXISTS FinancialTransaction (
    id TEXT NOT NULL,
    CONSTRAINT pk_financialtransaction PRIMARY KEY (id),
    user_id TEXT NOT NULL,
    type TEXT NOT NULL, -- include internal transfer, credit card balance payment, payment, and deposit and validate the from's and to's accordingly
    amount INTEGER NOT NULL,
    from_account_id TEXT,
    to_account_id TEXT,
    from_name TEXT,
    to_name TEXT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id)
        REFERENCES NappsUser(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_from_account FOREIGN KEY (from_account_id)
        REFERENCES Account(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_to_account FOREIGN KEY (to_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_transaction_user_id ON FinancialTransaction(user_id);
CREATE INDEX IF NOT EXISTS idx_transaction_type ON FinancialTransaction(type);
CREATE INDEX IF NOT EXISTS idx_transaction_from_account_id ON FinancialTransaction(from_account_id);
CREATE INDEX IF NOT EXISTS idx_transaction_to_account_id ON FinancialTransaction(to_account_id);
CREATE INDEX IF NOT EXISTS idx_transaction_date ON FinancialTransaction(transaction_date);
