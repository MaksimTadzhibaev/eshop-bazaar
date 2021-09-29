package ru.tadzh.controller.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class ProviderDto {
    private Long id;

    private String title;

    public ProviderDto() {
    }

    public ProviderDto(Long id) {
        this.id = id;
    }

    public ProviderDto(Long id, String title) {
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
