package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.persist.repository.ProductCategoryRepository;
import ru.tadzh.persist.repository.ProviderRepository;
import ru.tadzh.service.PictureService;
import ru.tadzh.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProviderRepository providerRepository;
    private final PictureService pictureService;

    @Autowired
    public ProductController(ProductService productService,
                             ProductCategoryRepository productCategoryRepository,
                             ProviderRepository providerRepository,
                             PictureService pictureService) {
        this.productService = productService;
        this.productCategoryRepository = productCategoryRepository;
        this.providerRepository = providerRepository;
        this.pictureService = pictureService;
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
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", productCategoryRepository.findAll());
        model.addAttribute("providers", providerRepository.findAll());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        model.addAttribute("categories", productCategoryRepository.findAll());
        model.addAttribute("providers", providerRepository.findAll());
        return "product_form";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {
        logger.info("Saving product or save product changes");
        if (result.hasErrors()) {
            model.addAttribute("categories", productCategoryRepository.findAll());
            model.addAttribute("providers", providerRepository.findAll());
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Delete product");
        productService.deleteById(id);
        model.addAttribute("categories", productCategoryRepository.findAll());
        model.addAttribute("providers", providerRepository.findAll());
        return "redirect:/product";
    }
}

