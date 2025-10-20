CREATE TABLE IF NOT EXISTS company_industry (
    process_id        INTEGER,
    corporate_number  BIGINT,
    business_item_id  INTEGER,
    FOREIGN KEY (process_id) REFERENCES process(id) ON DELETE CASCADE
);