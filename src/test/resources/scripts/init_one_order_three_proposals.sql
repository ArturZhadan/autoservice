INSERT INTO orders (id, description, acceptance_date, order_status, order_price, completion_date)
            VALUES (1, 'description1', '2022-12-10', 'PAID', 2500, '2022-12-20');
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (1, 2700, 'PAID', 1);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (2, 3100, 'PAID', 1);
INSERT INTO proposals (id, proposal_price, proposal_status, order_id) VALUES (3, 4200, 'PAID', 1);
