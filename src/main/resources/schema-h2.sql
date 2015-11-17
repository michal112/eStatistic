CREATE TABLE IF NOT EXISTS bull (
    id bigint NOT NULL,
    name varchar(255),
    number varchar(255),
    CONSTRAINT bull_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cow (
    id bigint NOT NULL,
    birth timestamp,
    book varchar(255),
    name varchar(255),
    number varchar(255),
    cow_parent bigint,
    CONSTRAINT cow_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cow_parent (
    id bigint NOT NULL,
    name varchar(255),
    number varchar(255),
    CONSTRAINT cow_parent_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS insemination (
    id bigint NOT NULL,
    date timestamp,
    insemination_bull bigint,
    insemination_cow bigint,
    CONSTRAINT insemination_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lactation (
    id bigint NOT NULL,
    date timestamp,
    lactation_cow bigint,
    number int,
    CONSTRAINT lactation_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE insemination DROP CONSTRAINT IF EXISTS fk_insemination_bull;
ALTER TABLE insemination ADD CONSTRAINT fk_insemination_bull FOREIGN KEY (insemination_bull) REFERENCES bull(id);

ALTER TABLE insemination DROP CONSTRAINT IF EXISTS fk_insemination_cow;
ALTER TABLE insemination ADD CONSTRAINT fk_insemination_cow FOREIGN KEY (insemination_cow) REFERENCES cow(id);

ALTER TABLE lactation DROP CONSTRAINT IF EXISTS fk_lactation_cow;
ALTER TABLE lactation ADD CONSTRAINT fk_lactation_cow FOREIGN KEY (lactation_cow) REFERENCES cow(id);

ALTER TABLE cow DROP CONSTRAINT IF EXISTS fk_cow_parent;
ALTER TABLE cow ADD CONSTRAINT fk_cow_parent FOREIGN KEY (cow_parent) REFERENCES cow_parent(id);
