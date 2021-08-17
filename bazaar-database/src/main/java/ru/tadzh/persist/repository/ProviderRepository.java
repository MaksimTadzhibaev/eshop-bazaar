package ru.tadzh.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.tadzh.persist.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider> {
}