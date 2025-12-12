-- Enable pgcrypto extension for UUID generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ==========================
-- User Table
-- ==========================
CREATE TABLE "User" (
    id UUID DEFAULT gen_random_uuid(),
    CONSTRAINT pk_user PRIMARY KEY (id),
    full_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255),
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for User
CREATE INDEX idx_user_username ON "User"(username);
CREATE INDEX idx_user_email ON "User"(email);

-- ==========================
-- Account Table
-- ==========================
CREATE TABLE Account (
    id UUID DEFAULT gen_random_uuid(),
    CONSTRAINT pk_account PRIMARY KEY (id),
    type VARCHAR(50) NOT NULL CHECK (type IN (
        'Checking', 'Savings', 'Retirement', 'Investment',
        'Cash', 'Credit Card', 'Health Savings Account', 'Deposit Account'
    )),
    name VARCHAR(255) NOT NULL,
    institution VARCHAR(255) NOT NULL,
    credit_limit INTEGER,
    image_url VARCHAR(255),
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for Account
CREATE INDEX idx_account_type ON Account(type);
CREATE INDEX idx_account_institution ON Account(institution);

-- ==========================
-- AccountPartition Table
-- ==========================
CREATE TABLE AccountPartition (
    id UUID DEFAULT gen_random_uuid(),
    CONSTRAINT pk_accountpartition PRIMARY KEY (id),
    parent_account_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    balance INTEGER NOT NULL,
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_accountpartition_account FOREIGN KEY (parent_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

-- Indexes for AccountPartition
CREATE INDEX idx_accountpartition_parent_account_id ON AccountPartition(parent_account_id);

-- Prevent duplicate partition names under the same parent account
CREATE UNIQUE INDEX uq_accountpartition_parent_name ON AccountPartition(parent_account_id, name);

-- ==========================
-- AccountOwnership Table
-- ==========================
CREATE TABLE AccountOwnership (
    id UUID DEFAULT gen_random_uuid(),
    CONSTRAINT pk_accountownership PRIMARY KEY (id),
    user_id UUID NOT NULL,
    account_id UUID NOT NULL,
    ownership_type VARCHAR(50) NOT NULL CHECK (ownership_type IN ('Owner', 'Joint Owner', 'Viewer')),
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_accountownership_user FOREIGN KEY (user_id)
        REFERENCES "User"(id) ON DELETE CASCADE,
    CONSTRAINT fk_accountownership_account FOREIGN KEY (account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

-- Indexes for AccountOwnership
CREATE INDEX idx_accountownership_user_id ON AccountOwnership(user_id);
CREATE INDEX idx_accountownership_account_id ON AccountOwnership(account_id);
CREATE INDEX idx_accountownership_type ON AccountOwnership(ownership_type);

-- Prevent duplicate ownership records for the same user/account pair
CREATE UNIQUE INDEX uq_accountownership_user_account ON AccountOwnership(user_id, account_id);

-- ==========================
-- Transaction Table
-- ==========================
CREATE TABLE Transaction (
    id UUID DEFAULT gen_random_uuid(),
    CONSTRAINT pk_transaction PRIMARY KEY (id),
    user_id UUID NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('Payment', 'Deposit', 'Transfer', 'Credit Card Balance Payment')),
    from_account_id UUID,
    to_account_id UUID,
    from_name VARCHAR(255),
    to_name VARCHAR(255),
    transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id)
        REFERENCES "User"(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_from_account FOREIGN KEY (from_account_id)
        REFERENCES Account(id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_to_account FOREIGN KEY (to_account_id)
        REFERENCES Account(id) ON DELETE CASCADE
);

-- Indexes for Transaction
CREATE INDEX idx_transaction_user_id ON Transaction(user_id);
CREATE INDEX idx_transaction_type ON Transaction(type);
CREATE INDEX idx_transaction_from_account_id ON Transaction(from_account_id);
CREATE INDEX idx_transaction_to_account_id ON Transaction(to_account_id);
CREATE INDEX idx_transaction_date ON Transaction(transaction_date);

-- ==========================
-- Audit Trigger Function
-- ==========================
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.date_updated = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ==========================
-- Triggers for User
-- ==========================
CREATE TRIGGER trg_user_update
BEFORE UPDATE ON "User"
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- ==========================
-- Triggers for Account
-- ==========================
CREATE TRIGGER trg_account_update
BEFORE UPDATE ON Account
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- ==========================
-- Triggers for AccountPartition
-- ==========================
CREATE TRIGGER trg_accountpartition_update
BEFORE UPDATE ON AccountPartition
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- ==========================
-- Triggers for AccountOwnership
-- ==========================
CREATE TRIGGER trg_accountownership_update
BEFORE UPDATE ON AccountOwnership
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- ==========================
-- Triggers for Transaction
-- ==========================
CREATE TRIGGER trg_transaction_update
BEFORE UPDATE ON Transaction
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();