package pl.aogiri.ims.customer.domain.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.repository.BaseSpecifications;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.presentation.dto.CustomerFilterCriteria;
import pl.aogiri.ims.product.domain.entity.ProductEntity;
import pl.aogiri.ims.product.presentation.dto.ProductFilterCriteria;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerSpecifications implements BaseSpecifications<CustomerEntity, CustomerFilterCriteria> {

    @Override
    public Specification<CustomerEntity> filterByCriteria(CustomerFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}