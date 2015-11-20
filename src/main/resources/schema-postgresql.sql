CREATE TABLE IF NOT EXISTS bull (
    id bigint NOT NULL,
    name character varying(255),
    number character varying(255),
    CONSTRAINT bull_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cow (
    id bigint NOT NULL,
    birth timestamp without time zone,
    book character varying(255),
    name character varying(255),
    number character varying(255),
    cow_parent bigint,
    CONSTRAINT cow_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cow_parent (
    id bigint NOT NULL,
    name character varying(255),
    number character varying(255),
    CONSTRAINT cow_parent_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS insemination (
    id bigint NOT NULL,
    date timestamp without time zone,
    insemination_bull bigint,
    insemination_cow bigint,
    CONSTRAINT insemination_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lactation (
    id bigint NOT NULL,
    date timestamp without time zone,
    lactation_cow bigint,
    number integer,
    CONSTRAINT lactation_pkey PRIMARY KEY (id)
);

ALTER TABLE ONLY insemination DROP CONSTRAINT IF EXISTS fk_insemination_bull;
ALTER TABLE ONLY insemination ADD CONSTRAINT fk_insemination_bull FOREIGN KEY (insemination_bull) REFERENCES bull(id);

ALTER TABLE ONLY insemination DROP CONSTRAINT IF EXISTS fk_insemination_cow;
ALTER TABLE ONLY insemination ADD CONSTRAINT fk_insemination_cow FOREIGN KEY (insemination_cow) REFERENCES cow(id);

ALTER TABLE ONLY lactation DROP CONSTRAINT IF EXISTS fk_lactation_cow;
ALTER TABLE ONLY lactation ADD CONSTRAINT fk_lactation_cow FOREIGN KEY (lactation_cow) REFERENCES cow(id);

ALTER TABLE ONLY cow DROP CONSTRAINT IF EXISTS fk_cow_parent;
ALTER TABLE ONLY cow ADD CONSTRAINT fk_cow_parent FOREIGN KEY (cow_parent) REFERENCES cow_parent(id);
