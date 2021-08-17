package ru.tadzh.persist;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.persist.entity.Provider;

public class ProviderSpecifications {
    public static Specification<Provider> providerTitlePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }
}
