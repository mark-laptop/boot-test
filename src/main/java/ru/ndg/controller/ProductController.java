package ru.ndg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ndg.service.ProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProductsPage(@RequestParam(name = "min", required = false) Integer min
            , @RequestParam(name = "max", required = false) Integer max
            , @RequestParam(name = "p") Integer page
            , @RequestParam(name = "pc", defaultValue = "5", required = false) Integer pageCount
            , Model model) {
        model.addAttribute("products", productService.getAllProductsPage(min, max, page - 1, pageCount));
        return "products";
    }
}
