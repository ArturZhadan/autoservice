--liquibase formatted sql
--changeset <postgres>:<create-products-table>
CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(256) NOT NULL,
    product_price decimal NOT NULL,
    CONSTRAINT products_pk PRIMARY KEY (id)
);
--rollback DROP TABLE public.products;
