package ru.ndg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ndg.model.Product;
import ru.ndg.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProductsPage(Integer min, Integer max, Integer page, Integer pageCount) {
        if (page < 0) page = 0;
        if (min != null && max != null) {
            return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(PageRequest.of(page, pageCount), min, max);
        } else if (min != null) {
            return productRepository.findAllByPriceGreaterThanEqual(PageRequest.of(page, pageCount), min);
        } else if (max != null) {
            return productRepository.findAllByPriceLessThanEqual(PageRequest.of(page, pageCount), max);
        } else {
            return productRepository.findAll(PageRequest.of(page, pageCount));
        }
    }
}
