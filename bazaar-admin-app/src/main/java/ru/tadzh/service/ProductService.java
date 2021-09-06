package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.controller.ProductListParams;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.persist.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    Page<ProductDto> findWithFilter(ProductListParams productListParams);

    Optional<ProductDto> findById(Long id);

    void save(ProductDto productDto);

    void deleteById(Long id);
}

