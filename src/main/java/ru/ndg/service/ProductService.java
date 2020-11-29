package ru.ndg.service;

import org.springframework.data.domain.Page;
import ru.ndg.model.Product;

public interface ProductService {

    Page<Product> getAllProductsPage(String titlePart, Integer minPrice, Integer maxPrice, Integer page, Integer pageCount);

}
