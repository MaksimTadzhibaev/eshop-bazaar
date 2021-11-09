package ru.tadzh.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tadzh.controller.dto.ProductDto;
import ru.tadzh.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testIfNewCartIsEmpty() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddProduct() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setCost(new BigDecimal(123));
        expectedProduct.setTitle("Product name");
        cartService.addProductQty(expectedProduct, "color", "material", 2);
        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());
        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(2, lineItem.getQty());
        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductDto());
        assertEquals(expectedProduct.getTitle(), lineItem.getProductDto().getTitle());
    }

    @Test
    public void testUpdateProduct() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setCost(new BigDecimal(123));
        expectedProduct.setTitle("Product name");
        cartService.addProductQty(expectedProduct, "color", "material", 2);
        expectedProduct.setCost(new BigDecimal(321));
        cartService.updateProduct(expectedProduct, "color", "material", 2);
        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());
        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(new BigDecimal(321), lineItem.getProductDto().getCost());
        assertEquals(2, lineItem.getQty());
    }

    @Test
    public void testRemoveProduct() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setCost(new BigDecimal(123));
        expectedProduct.setTitle("Product name");
        cartService.addProductQty(expectedProduct, "color", "material", 2);
        cartService.removeProduct(expectedProduct, "color", "material");
        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testGetSubtotal() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setCost(new BigDecimal(123));
        expectedProduct.setTitle("Product name");
        cartService.addProductQty(expectedProduct, "color", "material", 1);
        assertEquals(new BigDecimal(123) , cartService.getSubTotal());
    }

    @Test
    public void testRemoveAllProducts() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setCost(new BigDecimal(123));
        expectedProduct.setTitle("Product name");
        cartService.addProductQty(expectedProduct, "color1", "material1", 1);
        cartService.addProductQty(expectedProduct, "color2", "material2",2);
        assertNotNull(cartService.getLineItems());
        assertEquals(2, cartService.getLineItems().size());
        cartService.removeAllProducts();
        assertEquals(0, cartService.getLineItems().size());

    }
}