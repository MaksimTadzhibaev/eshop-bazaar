package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.controller.dto.CategoryDto;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.controller.dto.ProviderDto;
import ru.tadzh.persist.ProductSpecifications;
import ru.tadzh.persist.entity.Picture;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.repository.ProductRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<Long> categoryId, Optional<Long> providerId, Optional<String> title,
                                    Integer page, Integer size, String sortField) {
        Specification<Product> spec = Specification.where(null);
        if (categoryId.isPresent() && categoryId.get() != -1) {
            spec = spec.and(ProductSpecifications.byCategory(categoryId.get()));
        }
        if (providerId.isPresent() && providerId.get() != -1) {
            spec = spec.and(ProductSpecifications.byProvider(providerId.get()));
        }
        if (title.isPresent()) {
            spec = spec.and(ProductSpecifications.productTitlePrefix(title.get()));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getCost(),
                        new CategoryDto(product.getProductCategory().getId(), product.getProductCategory().getTitle()),
                        new ProviderDto(product.getProvider().getId(), product.getProvider().getTitle()),
                        product.getPictures().stream()
                                .map(Picture::getId)
                                .collect(Collectors.toList())));
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getCost(),
                        new CategoryDto(product.getProductCategory().getId(), product.getProductCategory().getTitle()),
                        new ProviderDto(product.getProvider().getId(), product.getProvider().getTitle()),
                        product.getPictures().stream()
                                .map(Picture::getId)
                                .collect(Collectors.toList())));
    }
}