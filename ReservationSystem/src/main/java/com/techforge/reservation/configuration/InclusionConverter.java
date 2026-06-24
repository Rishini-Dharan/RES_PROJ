package com.techforge.reservation.configuration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class InclusionConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return String.join(",", strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s == null || s.isBlank()) {
            return List.of();
        }

        return Arrays.stream(s.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
