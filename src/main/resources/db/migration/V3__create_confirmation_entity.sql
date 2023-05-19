CREATE TABLE confirmations
(
    id         UUID         NOT NULL,
    type       VARCHAR(255) NOT NULL,
    invoice_id UUID         NOT NULL,
    file       VARCHAR(1024),
    CONSTRAINT pk_confirmations PRIMARY KEY (id)
);