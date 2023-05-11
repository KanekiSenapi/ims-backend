package pl.aogiri.ims.product.domain.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.repository.BaseSpecifications;
import pl.aogiri.ims.product.domain.entity.ProductEntity;
import pl.aogiri.ims.product.presentation.dto.ProductFilterCriteria;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecifications implements BaseSpecifications<ProductEntity, ProductFilterCriteria> {

    @Override
    public Specification<ProductEntity> filterByCriteria(ProductFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null) {
                predicates.add(cb.equal(root.get("name"), criteria.getName()));
            }
            if (criteria.getCustomerId() != null) {
                predicates.add(cb.equal(root.get("customer").get("id"), criteria.getCustomerId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}