package pl.aogiri.ims.customer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
