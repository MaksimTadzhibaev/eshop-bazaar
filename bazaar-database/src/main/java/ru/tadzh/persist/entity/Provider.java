package ru.tadzh.persist.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProvider")
    private Long id;

    @Column(name = "titleProvider", nullable = false)
    private String title;

    @OneToMany(mappedBy = "provider")
    private List<Product> products;

    public Provider(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Provider() {
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