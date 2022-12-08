--liquibase formatted sql
--changeset <postgres>:<create-proposals-table>
CREATE TABLE IF NOT EXISTS public.proposals
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    worker_id bigint NOT NULL REFERENCES workers (id),
    proposal_price decimal NOT NULL,
    proposal_status character varying(256) NOT NULL,
    CONSTRAINT proposals_pk PRIMARY KEY (id)
);
--rollback DROP TABLE public.proposals;
