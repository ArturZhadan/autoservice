--liquibase formatted sql
--changeset <postgres>:<create-owners-table>
CREATE TABLE IF NOT EXISTS public.owners
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    car_id bigint NOT NULL REFERENCES cars (id),
    order_id bigint NOT NULL REFERENCES orders (id),
    CONSTRAINT owners_pk PRIMARY KEY (id)
    );
--rollback DROP TABLE public.owners;
