package ru.tadzh.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.entity.ProductCategory;
import ru.tadzh.persist.entity.Provider;
import ru.tadzh.persist.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
        expectedCategory.setTitle("Category title");

        Provider expectedProvider = new Provider();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("Provider title");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Product title");
        expectedProduct.setProductCategory(expectedCategory);
        expectedProduct.setProvider(expectedProvider);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setCost(new BigDecimal(123));

        when(productRepository.findById(eq(expectedProduct.getId())))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductDto> opt = productService.findById(expectedProduct.getId());

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getTitle(), opt.get().getTitle());
    }

    @Test
    public void testFindAll() {
        ProductCategory expectedCategory = new ProductCategory();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("ProductCategory title");

        Provider expectedProvider = new Provider();
        expectedProvider.setId(1L);
        expectedProvider.setTitle("Provider title");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Product title");
        expectedProduct.setProductCategory(expectedCategory);
        expectedProduct.setProvider(expectedProvider);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setCost(new BigDecimal(123));

        when(productRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl(List.of(expectedProduct)));

        Page<ProductDto> products = productService.findAll(Optional.of(expectedCategory.getId()), Optional.of(expectedProvider.getId()),
                Optional.of( expectedProduct.getTitle()), 1,3,"id");

        ProductDto expected = products.getContent().get(0);
        assertEquals(expected.getId(), expectedProduct.getId());
        assertEquals(expected.getCategory().getTitle(), expectedProduct.getProductCategory().getTitle());
        assertEquals(expected.getTitle(), expectedProduct.getTitle());
        assertEquals(expected.getCost(), expectedProduct.getCost());
    }
}
