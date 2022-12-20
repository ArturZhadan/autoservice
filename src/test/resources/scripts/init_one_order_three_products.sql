INSERT INTO products (id, name, product_price) VALUES (1, 'engine', 2000);
INSERT INTO products (id, name, product_price) VALUES (2, 'wheel', 900);
INSERT INTO products (id, name, product_price) VALUES (3, 'oil', 250);
INSERT INTO orders (id, products) VALUES (1, 1), (1, 2), (1, 3);
INSERT INTO orders_products (orders_id, products_id) VALUES (1, 1);
INSERT INTO orders_products (orders_id, products_id) VALUES (1, 2);
INSERT INTO orders_products (orders_id, products_id) VALUES (1, 3);
