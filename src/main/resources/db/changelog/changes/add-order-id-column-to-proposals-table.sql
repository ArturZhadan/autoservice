ALTER TABLE proposals ADD COLUMN order_id bigint NOT NULL REFERENCES orders (id);
