ALTER TABLE cars ADD COLUMN owner_id bigint NOT NULL REFERENCES owners (id);
