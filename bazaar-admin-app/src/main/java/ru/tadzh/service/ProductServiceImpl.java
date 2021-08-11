package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.controller.ProductListParams;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.repository.ProductRepository;
import ru.tadzh.persist.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec = Specification.where(null);

        if (productListParams.getProductTitleFilter() != null && !productListParams.getProductTitleFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.productTitlePrefix(productListParams.getProductTitleFilter()));
        }
        if (productListParams.getMinCost() != null) {
            spec = spec.and(ProductSpecifications.minCost(productListParams.getMinCost()));
        }
        if (productListParams.getMaxCost() != null) {
            spec = spec.and(ProductSpecifications.maxCost(productListParams.getMaxCost()));
        }


        Sort sort;
        if (productListParams.getSorting() != null && !productListParams.getSorting().isBlank()) {
            sort = Sort.by(productListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (productListParams.getSortingParam() != null && !productListParams.getSortingParam().isBlank() && productListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return productRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(productListParams.getSize()).orElse(3), sort));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
