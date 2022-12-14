package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.ProductRequestDto;
import com.example.springboot.autoservice.dto.response.ProductResponseDto;
import com.example.springboot.autoservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setProductPrice(product.getProductPrice());
        return dto;
    }

    public Product toModel(ProductRequestDto dto) {
        Product model = new Product();
        model.setName(dto.getName());
        model.setProductPrice(dto.getProductPrice());
        return model;
    }
}
