package ru.tadzh.controller.dto;

import javax.validation.constraints.NotBlank;

public class ProductCategoryDto {
    private Long id;

    @NotBlank
    private String title;

    public ProductCategoryDto() {
    }

    public ProductCategoryDto(Long id) {
        this.id = id;
    }

    public ProductCategoryDto(Long id, String title) {
        this.id = id;
        this.title = title;
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
}