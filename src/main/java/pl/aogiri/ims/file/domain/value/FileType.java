package pl.aogiri.ims.file.domain.value;

import lombok.Getter;

public enum FileType {
    INVOICE("faktura"),
    CONFIRMATION_TRANSPORT("potwierdzenie_transport"),
    CONFIRMATION_TRANSFER("potwierdzenie_przelew");

    @Getter
    private final String friendlyName;

    FileType(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
