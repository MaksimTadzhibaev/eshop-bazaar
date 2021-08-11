package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tadzh.persist.entity.Product;
import ru.tadzh.persist.repository.ProductCategoryRepository;
import ru.tadzh.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryRepository productCategoryRepository) {
        this.productService = productService;
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping
    public String listPage(Model model, ProductListParams productListParams) {
        logger.info("Product list page requested");
        model.addAttribute("products", productService.findWithFilter
                (productListParams));
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        logger.info("New product page requested");
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productCategoryRepository.findAll());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", productCategoryRepository.findAll());
        return "product_form";
    }

    @PostMapping
    public String update(Product product, Model model) {
        logger.info("Saving product or save product changes");
        productService.save(product);
        model.addAttribute("categories", productCategoryRepository.findAll());
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Delete product");
        productService.deleteById(id);
        model.addAttribute("categories", productCategoryRepository.findAll());
        return "redirect:/product";
    }
}

