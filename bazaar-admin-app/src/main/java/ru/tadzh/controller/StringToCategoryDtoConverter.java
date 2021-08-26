package ru.tadzh.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tadzh.controller.dto.ProductCategoryDto;

@Component
public class StringToCategoryDtoConverter implements Converter<String, ProductCategoryDto> {

    @Override
    public ProductCategoryDto convert(String id) {
        return new ProductCategoryDto(Long.parseLong(id));
    }
}
