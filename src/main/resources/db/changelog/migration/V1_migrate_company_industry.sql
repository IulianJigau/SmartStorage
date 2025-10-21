CREATE TABLE IF NOT EXISTS company_industry (
    process_id        INTEGER NOT NULL,
    corporate_number  BIGINT,
    business_item_id  INTEGER,
    update_date       TIMESTAMPTZ,
    FOREIGN KEY (process_id) REFERENCES process(id) ON DELETE CASCADE
);