--liquibase formatted sql
--changeset <postgres>:<create-workers-table>
CREATE TABLE IF NOT EXISTS public.workers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name character varying(256) NOT NULL,
    last_name character varying(256) NOT NULL,
    CONSTRAINT workers_pk PRIMARY KEY (id)
);
--rollback DROP TABLE public.workers;
