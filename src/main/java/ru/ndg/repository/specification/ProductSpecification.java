package ru.ndg.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ndg.model.Product;

public class ProductSpecification {

    public static Specification<Product> priceGreaterOrEqualsThan(Integer minPrice) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessOrEqualThan(Integer maxPrice) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleLikeThan(String titlePart) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
