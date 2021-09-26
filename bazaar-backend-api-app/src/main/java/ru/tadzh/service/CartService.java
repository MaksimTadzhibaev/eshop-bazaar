package ru.tadzh.service;

import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addProductQty(ProductDto productDto, String color, String material, int qty);

    void updateProduct(ProductDto productDto, String color, String material, int qty);

    void removeProduct(ProductDto productDto, String color, String material);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

    void removeAllProducts();
}