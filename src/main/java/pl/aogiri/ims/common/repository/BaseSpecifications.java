package pl.aogiri.ims.common.repository;

import org.springframework.data.jpa.domain.Specification;

public interface BaseSpecifications<Entity, Criteria> {

    Specification<Entity> filterByCriteria(Criteria baseFilterCriteria);
}
