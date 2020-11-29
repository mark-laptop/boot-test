package ru.ndg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ndg.model.Product;
import ru.ndg.repository.ProductRepository;
import ru.ndg.repository.specification.ProductSpecification;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProductsPage(Map<String, String> params, Integer page) {
        if (page < 0) page = 0;
        String pc = params.get("pc");
        int pageCount = 5;
        if (pc != null && Integer.parseInt(pc) > 0) pageCount = Integer.parseInt(pc);

        Specification<Product> specification = Specification.where(null);
        String minPrice = params.get("min_price");
        if (minPrice != null && !minPrice.isEmpty() && !Character.isWhitespace(minPrice.charAt(0))) {
            specification = specification.and(ProductSpecification.priceGreaterOrEqualsThan(Integer.parseInt(minPrice)));
        }
        String maxPrice = params.get("max_price");
        if (maxPrice != null && !maxPrice.isEmpty() && !Character.isWhitespace(maxPrice.charAt(0))) {
            specification = specification.and(ProductSpecification.priceLessOrEqualThan(Integer.parseInt(maxPrice)));
        }
        String titlePart = params.get("title_part");
        if (titlePart != null && !titlePart.isEmpty() && !Character.isWhitespace(titlePart.charAt(0))) {
            specification = specification.and(ProductSpecification.titleLikeThan(titlePart));
        }
        return productRepository.findAll(specification, PageRequest.of(page, pageCount));
    }
}
