CREATE TABLE invoice_items
(
    id                 UUID         NOT NULL,
    product_id         UUID         NOT NULL,
    quantity           INTEGER      NOT NULL,
    unit               VARCHAR(255) NOT NULL,
    tax                INTEGER      NOT NULL,
    total_net_amount   DECIMAL      NOT NULL,
    total_gross_amount DECIMAL      NOT NULL,
    CONSTRAINT pk_invoice_items PRIMARY KEY (id)
);

ALTER TABLE invoice_items
    ADD CONSTRAINT FK_INVOICE_ITEMS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

CREATE TABLE invoices
(
    id              UUID         NOT NULL,
    invoice_number  VARCHAR(255) NOT NULL,
    invoice_date    date         NOT NULL,
    sale_date       date         NOT NULL,
    issue_place     VARCHAR(255) NOT NULL,
    payment_method  VARCHAR(255) NOT NULL,
    seller_id       UUID         NOT NULL,
    buyer_id        UUID         NOT NULL,
    additional_info VARCHAR(1024),
    CONSTRAINT pk_invoices PRIMARY KEY (id)
);

CREATE TABLE invoices_items
(
    invoice_entity_id UUID NOT NULL,
    items_id          UUID NOT NULL
);

ALTER TABLE invoices_items
    ADD CONSTRAINT uc_invoices_items_items UNIQUE (items_id);

ALTER TABLE invoices
    ADD CONSTRAINT FK_INVOICES_ON_BUYER FOREIGN KEY (buyer_id) REFERENCES customers (id);

ALTER TABLE invoices
    ADD CONSTRAINT FK_INVOICES_ON_SELLER FOREIGN KEY (seller_id) REFERENCES customers (id);

ALTER TABLE invoices_items
    ADD CONSTRAINT fk_invite_on_invoice_entity FOREIGN KEY (invoice_entity_id) REFERENCES invoices (id);

ALTER TABLE invoices_items
    ADD CONSTRAINT fk_invite_on_invoice_item_entity FOREIGN KEY (items_id) REFERENCES invoice_items (id);