package ru.tadzh.persist;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.persist.entity.ProductCategory;

public class ProductCategorySpecifications {

    public static Specification<ProductCategory> productCategoryTitlePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }
}