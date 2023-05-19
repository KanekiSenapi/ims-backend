package pl.aogiri.ims.invoice.domain.entity;

import lombok.Getter;

public enum InvoiceType {
    PURCHASE("koszt"),
    SALES("sprzedaz");

    @Getter
    private final String friendlyName;

    InvoiceType(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
