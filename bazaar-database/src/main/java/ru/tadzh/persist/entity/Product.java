package ru.tadzh.persist.entity;

import javax.persistence.*;

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
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Product(String name, Double cost) {
        this.title = name;
        this.cost = cost;
    }

    public Product(String name, Double cost, ProductCategory productCategory, Provider provider) {
        this.provider = provider;
        this.productCategory = productCategory;
        this.title = name;
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

    public Double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Double cost) {
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
}
