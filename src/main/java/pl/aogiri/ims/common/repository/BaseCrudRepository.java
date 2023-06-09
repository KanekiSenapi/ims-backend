package pl.aogiri.ims.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseCrudRepository<Entity> extends JpaRepository<Entity, UUID>, JpaSpecificationExecutor<Entity> {
}
