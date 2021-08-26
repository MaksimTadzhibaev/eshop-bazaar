package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tadzh.controller.ProductListParams;
import ru.tadzh.controller.dto.ProductCategoryDto;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.controller.dto.ProviderDto;
import ru.tadzh.persist.entity.Picture;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.entity.ProductCategory;
import ru.tadzh.persist.entity.Provider;
import ru.tadzh.persist.repository.ProductRepository;
import ru.tadzh.persist.ProductSpecifications;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PictureService pictureService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getCost(),
                        mapToProductCategoryDto(product.getProductCategory()),
                        mapToProviderDto(product.getProvider())))
                .collect(Collectors.toList());
    }


    private static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory) {
        return new ProductCategoryDto(productCategory.getId(), productCategory.getTitle());
    }

    private static ProviderDto mapToProviderDto(Provider provider) {
        return new ProviderDto(provider.getId(), provider.getTitle());
    }

    private static List<Long> mapToPictureIds(List<Picture> pictures) {
        return pictures.stream()
                .map(Picture::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> findWithFilter(ProductListParams productListParams) {
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
                        Optional.ofNullable(productListParams.getSize()).orElse(3), sort)).map(product -> {
            ProductDto productDto = new ProductDto(product.getId(), product.getTitle(), product.getCost(), mapToProductCategoryDto(product.getProductCategory()), mapToProviderDto(product.getProvider()));
            productDto.setProviderDto(mapToProviderDto(product.getProvider()));
            return productDto;
        });
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductDto productDto = new ProductDto(product.getId(),
                            product.getTitle(),
                            product.getCost(),
                            mapToProductCategoryDto(product.getProductCategory()),
                            mapToProviderDto(product.getProvider()));
                    productDto.setPictureId(mapToPictureIds(product.getPictures()));
                    return productDto;
                });
    }

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        ProductCategoryDto productCategoryDto = productDto.getProductCategoryDto();
        ProviderDto providerDto = productDto.getProviderDto();
        Product product = new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getCost(),
                new ProductCategory(productCategoryDto.getId(), productCategoryDto.getTitle()),
                new Provider(providerDto.getId(), providerDto.getTitle()));

        if (productDto.getNewPictures() != null) {
            for (MultipartFile newPicture : productDto.getNewPictures()) {
                try {
                    product.getPictures().add(new Picture(null,
                            newPicture.getOriginalFilename(),
                            newPicture.getContentType(),
                            pictureService.createPicture(newPicture.getBytes()),
                            product
                    ));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            productRepository.save(product);
        }
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
