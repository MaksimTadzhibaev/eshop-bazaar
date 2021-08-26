package ru.tadzh.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tadzh.controller.dto.ProviderDto;

@Component
public class StringToProviderDtoConverter implements Converter<String, ProviderDto> {

    @Override
    public ProviderDto convert(String id) {
        return new ProviderDto(Long.parseLong(id));
    }
}
