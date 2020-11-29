package ru.ndg.service;

import org.springframework.data.domain.Page;
import ru.ndg.model.Product;

import java.util.Map;

public interface ProductService {

    Page<Product> getAllProductsPage(Map<String, String> params, Integer page);

}
