INSERT INTO workers (id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (1, 'description1', '2022-12-10', 'PAID', 2500, '2022-12-20');
INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (2, 'description2', '2022-12-15', 'PAID', 3000, '2022-12-25');
INSERT INTO workers_orders (worker_id, orders_id) VALUES (1, 1), (1, 2);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (1, 1800, 'NOT_PAID', 1);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (2, 3500, 'NOT_PAID', 1);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (3, 2200, 'NOT_PAID', 2);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (4, 2800, 'NOT_PAID', 2);
