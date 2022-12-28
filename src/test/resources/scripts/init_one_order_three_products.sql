INSERT INTO products (id, name, product_price) VALUES (1, 'engine', 2000);
INSERT INTO products (id, name, product_price) VALUES (2, 'wheel', 900);
INSERT INTO products (id, name, product_price) VALUES (3, 'oil', 250);
INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (1, 'description1', '2022-12-10', 'PAID', 2500, '2022-12-20');
INSERT INTO orders_products (order_id, products_id) VALUES (1, 1), (1, 2), (1, 3);
