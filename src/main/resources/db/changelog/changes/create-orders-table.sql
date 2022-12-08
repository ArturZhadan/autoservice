--liquibase formatted sql
--changeset <postgres>:<create-orders-table>
CREATE TABLE IF NOT EXISTS public.orders
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    car_id bigint NOT NULL REFERENCES cars (id),
    description character varying(256) NOT NULL,
    acceptance_date date NOT NULL,
    proposal_id bigint NOT NULL REFERENCES proposals (id),
    product_id bigint NOT NULL REFERENCES products (id),
    order_status character varying(256) NOT NULL,
    order_price decimal NOT NULL,
    completion_date date NOT NULL,
    CONSTRAINT orders_pk PRIMARY KEY (id)
);
--rollback DROP TABLE public.orders;
