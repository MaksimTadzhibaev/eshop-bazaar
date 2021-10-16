package ru.tadzh.persist.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Long id;

    @Column(name = "titleProduct", nullable = false)
    private String title;

    @Column(name = "costProduct", nullable = false)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<OrderLineItem> orderLineItems;

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, BigDecimal cost, ProductCategory productCategory, Provider provider) {
        this.provider = provider;
        this.productCategory = productCategory;
        this.title = title;
        this.cost = cost;
    }

    public Product(Long id, String title, BigDecimal cost, ProductCategory productCategory, Provider provider) {
        this.id = id;
        this.provider = provider;
        this.productCategory = productCategory;
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
