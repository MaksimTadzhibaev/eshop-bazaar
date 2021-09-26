package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.controller.dto.AddLineItemDto;
import ru.tadzh.controller.dto.AllCartDto;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.service.CartService;
import ru.tadzh.service.ProductService;
import ru.tadzh.service.dto.LineItem;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<LineItem> addToCart(@RequestBody AddLineItemDto addLineItemDto) {
        logger.info("New LineItem. ProductId = {}, qty = {}", addLineItemDto.getProductId(), addLineItemDto.getQty());

        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQty(productDto, addLineItemDto.getColor(), addLineItemDto.getMaterial(), addLineItemDto.getQty());
        return cartService.getLineItems();
    }

    @GetMapping(value = "/all", produces = "application/json")
    public AllCartDto findAll() {
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }

    @DeleteMapping(produces = "application/json")
    public AllCartDto deleteAll() {
        logger.info("Clear cart");
        cartService.removeAllProducts();
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }

    @DeleteMapping(consumes = "application/json")
    public AllCartDto deleteLineItem(@RequestBody LineItem lineItem) {
        logger.info("Delete LineItem. ProductId = {}", lineItem.getProductId());
        cartService.removeProduct(lineItem.getProductDto(), lineItem.getColor(), lineItem.getMaterial());
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public AllCartDto updateLineItemQty(@RequestBody LineItem lineItem) {
        logger.info("Update LineItem. ProductId = {}, new qty = {}",
                lineItem.getProductId(), lineItem.getQty());
        cartService.updateProduct(lineItem.getProductDto(), lineItem.getColor(), lineItem.getMaterial(), lineItem.getQty());
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }
}
