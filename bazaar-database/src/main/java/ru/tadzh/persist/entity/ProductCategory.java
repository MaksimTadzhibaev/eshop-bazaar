package ru.tadzh.persist.entity;

import ru.tadzh.persist.entity.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductCategory")
    private Long id;

    @Column(name = "titleProductCategory", nullable = false)
    private String title;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    public ProductCategory(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
