package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tadzh.persist.entity.Provider;
import ru.tadzh.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
        ;
    }

    @GetMapping
    public String listPage(Model model, ProviderListParams providerListParams) {
        logger.info("Product category list page requested");
        model.addAttribute("providers", providerService.findWithFilter
                (providerListParams));
        return "providers";
    }

    @GetMapping("/new")
    public String newProviderForm(Model model) {
        logger.info("New provider page requested");
        model.addAttribute("provider", new Provider());
        return "provider_form";
    }

    @GetMapping("/{id}")
    public String editProvider(@PathVariable("id") Long id, Model model) {
        logger.info("Edit provider page requested");
        model.addAttribute("provider", providerService.findById(id));
        return "provider_form";
    }

    @PostMapping
    public String updateProvider(Provider provider) {
        logger.info("Saving provider or save product category changes");
        providerService.save(provider);
        return "redirect:/provider";
    }

    @GetMapping("/{id}/delete")
    public String deleteProvider(@PathVariable("id") Long id) {
        logger.info("Delete provider");
        providerService.deleteById(id);
        return "redirect:/provider";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
