package pl.aogiri.ims.confirmation.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;

import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "confirmations")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConfirmationType type;

    @ManyToOne
    private InvoiceEntity invoice;

    @Column(columnDefinition = "text")
    private String file;

    public URI getFile() {
        return URI.create(file);
    }

    public void setFile(URI uri) {
        this.file = uri.toString();
    }
}
