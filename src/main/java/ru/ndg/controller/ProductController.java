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
    public String getAllProductsPage(
            @RequestParam(name = "min_price", required = false) Integer min
            , @RequestParam(name = "max_price", required = false) Integer max
            , @RequestParam(name = "title_part", required = false) String titlePart
            , @RequestParam(name = "p", defaultValue = "1") Integer page
            , @RequestParam(name = "pc", defaultValue = "5", required = false) Integer pageCount
            , Model model) {
        model.addAttribute("products", productService.getAllProductsPage(titlePart, min, max, page - 1, pageCount));
        return "products";
    }
}
