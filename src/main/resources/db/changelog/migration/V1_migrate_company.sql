CREATE TABLE IF NOT EXISTS company (
    process_id              INTEGER NOT NULL,
    corporate_number        BIGINT,
    representative_position VARCHAR(100),
    representative_name     VARCHAR(255),
    postal_code             INTEGER,
    location                TEXT,
    name                    VARCHAR(255),
    kana                    VARCHAR(255),
    status                  VARCHAR(50),
    qualification_grade     VARCHAR(50),
    update_date             TIMESTAMPTZ,
    date_of_establishment   DATE,
    FOREIGN KEY (process_id) REFERENCES process(id) ON DELETE CASCADE
);