package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.controller.ProductCategoryListParams;
import ru.tadzh.persist.*;
import ru.tadzh.persist.entity.ProductCategory;
import ru.tadzh.persist.repository.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Page<ProductCategory> findWithFilter(ProductCategoryListParams productCategoryListParams) {
        Specification<ProductCategory> spec = Specification.where(null);

        if (productCategoryListParams.getProductCategoryTitleFilter() != null && !productCategoryListParams.getProductCategoryTitleFilter().isBlank()) {
            spec = spec.and(ProductCategorySpecifications.productCategoryTitlePrefix(productCategoryListParams.getProductCategoryTitleFilter()));
        }

        Sort sort;
        if (productCategoryListParams.getSorting() != null && !productCategoryListParams.getSorting().isBlank()) {
            sort = Sort.by(productCategoryListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (productCategoryListParams.getSortingParam() != null && !productCategoryListParams.getSortingParam().isBlank() && productCategoryListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return productCategoryRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(productCategoryListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(productCategoryListParams.getSize()).orElse(3), sort));
    }

    @Override
    public Optional<ProductCategory> findById(Long id) {
        return productCategoryRepository.findById(id);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteById(Long id) {
        productCategoryRepository.deleteById(id);
    }
}
