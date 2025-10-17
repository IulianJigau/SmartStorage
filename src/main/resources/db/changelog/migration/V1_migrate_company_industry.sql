CREATE TABLE IF NOT EXISTS company_industry (
    process_id        INTEGER,
    corporate_number  BIGINT,
    business_item_id  INTEGER,
    UNIQUE (process_id, corporate_number, business_item_id),
    FOREIGN KEY (process_id) REFERENCES process(id) ON DELETE CASCADE
);