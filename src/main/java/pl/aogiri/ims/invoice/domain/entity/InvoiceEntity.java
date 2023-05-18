package pl.aogiri.ims.invoice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String invoiceNumber;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false)
    private String issuePlace;

    @Column(nullable = false)
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomerEntity seller;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomerEntity buyer;

    @Column(length = 1024)
    private String additionalInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InvoiceItemEntity> items;

    private URI file;

    @Transient
    public BigDecimal getTotalGrossAmount() {
        return items.stream().map(InvoiceItemEntity::getTotalGrossAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InvoiceItemEntity that = (InvoiceItemEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
