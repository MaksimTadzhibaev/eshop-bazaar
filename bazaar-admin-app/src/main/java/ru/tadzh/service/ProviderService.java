package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.controller.ProviderListParams;
import ru.tadzh.persist.entity.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    List<Provider> findAll();

    Page<Provider> findWithFilter(ProviderListParams providerListParams);

    Optional<Provider> findById(Long id);

    void save(Provider provider);

    void deleteById(Long id);
}