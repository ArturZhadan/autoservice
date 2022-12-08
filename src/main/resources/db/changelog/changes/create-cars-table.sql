--liquibase formatted sql
--changeset <postgres>:<create-cars-table>
CREATE TABLE IF NOT EXISTS public.cars
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    manufacturer character varying(256) NOT NULL,
    model character varying(256) NOT NULL,
    year smallint NOT NULL,
    number character varying(256) NOT NULL,
    CONSTRAINT cars_pk PRIMARY KEY (id)
);
--rollback DROP TABLE public.cars;
