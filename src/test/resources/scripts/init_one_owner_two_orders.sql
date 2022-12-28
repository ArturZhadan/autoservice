INSERT INTO owners (id) VALUES (1);
INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (1, 'description1', '2022-12-10', 'PAID', 2500, '2022-12-20');
INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (2, 'description2', '2022-12-15', 'PAID', 3000, '2022-12-25');
INSERT INTO owners_orders (owner_id, orders_id) VALUES (1, 1), (1, 2);
