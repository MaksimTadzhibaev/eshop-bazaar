package ru.tadzh.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    private String title;

    private BigDecimal cost;

    private CategoryDto category;

    private ProviderDto provider;

    private List<Long> pictures;

    private Long mainPictureId;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal cost, CategoryDto category, ProviderDto provider, List<Long> pictures) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.category = category;
        this.provider = provider;
        this.pictures = pictures;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }

    public ProviderDto getProvider() {
        return provider;
    }

    public void setProvider(ProviderDto provider) {
        this.provider = provider;
    }

    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(Long mainPictureId) {
        this.mainPictureId = mainPictureId;
    }
}