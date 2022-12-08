ALTER TABLE workers ADD COLUMN order_id bigint NOT NULL REFERENCES orders (id);
