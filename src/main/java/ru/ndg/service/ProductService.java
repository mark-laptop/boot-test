package ru.ndg.service;

import org.springframework.data.domain.Page;
import ru.ndg.model.Product;

public interface ProductService {

    Page<Product> getAllProductsPage(Integer min, Integer max, Integer page, Integer pageCount);

}
