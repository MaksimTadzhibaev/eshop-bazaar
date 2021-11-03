package ru.tadzh.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.entity.ProductCategory;
import ru.tadzh.persist.entity.Provider;
import ru.tadzh.persist.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testFindById() {
        ProductCategory expectedCategory = new ProductCategory();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("Category name");

        Provider expectedProvider = new Provider();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("Provider name");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Product name");
        expectedProduct.setProductCategory(expectedCategory);
        expectedProduct.setProvider(expectedProvider);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setCost(new BigDecimal(12345));

        when(productRepository.findById(eq(expectedProduct.getId())))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductDto> opt = productService.findById(expectedProduct.getId());

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getTitle(), opt.get().getTitle());
    }

    @Test
    public void testFindAll() {
//        when(productRepository.findAll(any(Specification.class), any(Pageable.class)))
//                .thenReturn(Optional.of(expectedProduct));
    }

}