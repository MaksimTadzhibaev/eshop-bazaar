package ru.tadzh.controller;

public class ProviderListParams {
    private String providerTitleFilter;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getProviderTitleFilter() {
        return providerTitleFilter;
    }

    public void setProviderTitleFilter(String providerTitleFilter) {
        this.providerTitleFilter = providerTitleFilter;
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