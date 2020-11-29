package ru.ndg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ndg.service.ProductService;

import java.util.Map;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProductsPage(
            @RequestParam Map<String, String> params
            , @RequestParam(name = "p", defaultValue = "1") Integer page
            , Model model) {
        model.addAttribute("products", productService.getAllProductsPage(params, page - 1));
        return "products";
    }
}
