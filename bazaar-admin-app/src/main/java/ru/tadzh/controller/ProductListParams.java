package ru.tadzh.controller;

public class ProductListParams {
    private String productTitleFilter;
    private Double minCost;
    private Double maxCost;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getProductTitleFilter() {
        return productTitleFilter;
    }

    public void setProductTitleFilter(String productTitleFilter) {
        this.productTitleFilter = productTitleFilter;
    }

    public Double getMinCost() {
        return minCost;
    }

    public void setMinCost(Double minCost) {
        this.minCost = minCost;
    }

    public Double getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(Double maxCost) {
        this.maxCost = maxCost;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getSortingParam() {
        return sortingParam;
    }

    public void setSortingParam(String sortingParam) {
        this.sortingParam = sortingParam;
    }
}