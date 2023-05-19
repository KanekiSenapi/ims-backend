CREATE TABLE products
(
    id           UUID    NOT NULL,
    name         VARCHAR(255),
    net_unit_price DECIMAL NOT NULL,
    customer_id  UUID    NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);