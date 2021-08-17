package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.controller.ProviderListParams;
import ru.tadzh.persist.ProviderSpecifications;
import ru.tadzh.persist.entity.Provider;
import ru.tadzh.persist.repository.ProviderRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService{

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Page<Provider> findWithFilter(ProviderListParams providerListParams) {
        Specification<Provider> spec = Specification.where(null);

        if (providerListParams.getProviderTitleFilter() != null && !providerListParams.getProviderTitleFilter().isBlank()) {
            spec = spec.and(ProviderSpecifications.providerTitlePrefix(providerListParams.getProviderTitleFilter()));
        }

        Sort sort;
        if (providerListParams.getSorting() != null && !providerListParams.getSorting().isBlank()) {
            sort = Sort.by(providerListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (providerListParams.getSortingParam() != null && !providerListParams.getSortingParam().isBlank() && providerListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return providerRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(providerListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(providerListParams.getSize()).orElse(3), sort));
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public void save(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }
}