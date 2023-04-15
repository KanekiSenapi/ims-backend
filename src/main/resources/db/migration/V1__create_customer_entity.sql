CREATE TABLE customers
(
    id      UUID         NOT NULL,
    name    VARCHAR(255) NOT NULL,
    krs     VARCHAR(255) NOT NULL,
    nip     VARCHAR(255) NOT NULL,
    regon   VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_krs UNIQUE (krs);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_nip UNIQUE (nip);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_regon UNIQUE (regon);