package ru.tadzh.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    private Long id;

    private String title;

    private BigDecimal cost;

    private ProductCategoryDto productCategoryDto;

    private ProviderDto providerDto;

    private List<Long> pictureId = new ArrayList<>();

    private MultipartFile[] newPictures;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal cost, ProductCategoryDto productCategoryDto, ProviderDto providerDto) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.productCategoryDto = productCategoryDto;
        this.providerDto = providerDto;
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

    public ProductCategoryDto getProductCategoryDto() {
        return productCategoryDto;
    }

    public void setProductCategoryDto(ProductCategoryDto productCategoryDto) {
        this.productCategoryDto = productCategoryDto;
    }

    public ProviderDto getProviderDto() {
        return providerDto;
    }

    public void setProviderDto(ProviderDto providerDto) {
        this.providerDto = providerDto;
    }

    public List<Long> getPictureId() {
        return pictureId;
    }

    public void setPictureId(List<Long> pictureId) {
        this.pictureId = pictureId;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }


    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
}
