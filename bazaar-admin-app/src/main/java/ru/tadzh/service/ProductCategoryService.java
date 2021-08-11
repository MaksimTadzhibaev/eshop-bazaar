package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.controller.ProductCategoryListParams;
import ru.tadzh.persist.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    List<ProductCategory> findAll();

    Page<ProductCategory> findWithFilter(ProductCategoryListParams productCategoryListParams);

    Optional<ProductCategory> findById(Long id);

    void save(ProductCategory productCategory);

    void deleteById(Long id);
}
