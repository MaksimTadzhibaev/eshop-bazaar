package ru.tadzh.persist;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.persist.entity.Product;

public final class ProductSpecifications {

    public static Specification<Product> productTitlePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }

    public static Specification<Product> minCost(Double minCost) {
        return (root, query, builder) -> builder.ge(root.get("cost"), minCost);
    }

    public static Specification<Product> maxCost(Double maxCost) {
        return (root, query, builder) -> builder.le(root.get("cost"), maxCost);
    }

    public static Specification<Product> byCategory(long categoryId) {
        return (root, query, builder) -> builder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> byProvider(long providerId) {
        return (root, query, builder) -> builder.equal(root.get("provider").get("id"), providerId);
    }

}
