package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tadzh.persist.entity.ProductCategory;
import ru.tadzh.service.ProductCategoryService;

@Controller
@RequestMapping("/categories")
public class ProductCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductCategoryService productCategoryService;


    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
        ;
    }

    @GetMapping
    public String listPage(Model model, ProductCategoryListParams productCategoryListParams) {
        logger.info("Product category list page requested");
        model.addAttribute("product_category", productCategoryService.findWithFilter
                (productCategoryListParams));
        return "product_category";
    }

    @GetMapping("/new")
    public String newProductCategoryForm(Model model) {
        logger.info("New product category page requested");
        model.addAttribute("categories", new ProductCategory());
        return "productcategory_form";
    }

    @GetMapping("/{id}")
    public String editProductCategory(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product category page requested");
        model.addAttribute("categories", productCategoryService.findById(id));
        return "productcategory_form";
    }

    @PostMapping
    public String updateProductCategory(ProductCategory productCategory) {
        logger.info("Saving product category or save product category changes");
        productCategoryService.save(productCategory);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteProductCategory(@PathVariable("id") Long id) {
        logger.info("Delete product");
        productCategoryService.deleteById(id);
        return "redirect:/categories";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
