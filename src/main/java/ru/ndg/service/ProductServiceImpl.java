package ru.ndg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ndg.model.Product;
import ru.ndg.repository.ProductRepository;
import ru.ndg.repository.specification.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProductsPage(String titlePart, Integer minPrice, Integer maxPrice, Integer page, Integer pageCount) {
        if (page < 0) page = 0;
        if (pageCount < 1) pageCount = 5;

        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.priceLessOrEqualThan(maxPrice));
        }
        if (titlePart != null && !titlePart.isEmpty()) {
            specification = specification.and(ProductSpecification.titleLikeThan(titlePart));
        }
        return productRepository.findAll(specification, PageRequest.of(page, pageCount));
    }
}
