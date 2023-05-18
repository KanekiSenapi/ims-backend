package pl.aogiri.ims.invoice.domain.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.repository.BaseSpecifications;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.presentation.dto.InvoiceFilterCriteria;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceSpecifications implements BaseSpecifications<InvoiceEntity, InvoiceFilterCriteria> {

    @Override
    public Specification<InvoiceEntity> filterByCriteria(InvoiceFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getInvoiceNumber() != null) {
                predicates.add(cb.like(cb.lower(root.get("invoiceNumber")), "%" + criteria.getInvoiceNumber().toLowerCase() + "%"));
            }
            if (criteria.getSellerName() != null) {
                predicates.add(cb.like(cb.lower(root.get("sellerName")), "%" + criteria.getSellerName().toLowerCase() + "%"));
            }
            if (criteria.getBuyerName() != null) {
                predicates.add(cb.like(cb.lower(root.get("buyerName")), "%" + criteria.getBuyerName().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}