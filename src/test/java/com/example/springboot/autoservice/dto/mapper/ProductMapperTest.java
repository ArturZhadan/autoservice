package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.ProductRequestDto;
import com.example.springboot.autoservice.dto.response.ProductResponseDto;
import com.example.springboot.autoservice.model.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;
    private Product product;
    private ProductRequestDto productRequestDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Engine");
        product.setProductPrice(BigDecimal.valueOf(1000));
        productRequestDto = new ProductRequestDto();
        productRequestDto.setName("Engine");
        productRequestDto.setProductPrice(BigDecimal.valueOf(1000));
    }

    @Test
    public void toDto_Ok() {
        ProductResponseDto actual = productMapper.toDto(product);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals("Engine", actual.getName());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getProductPrice());
    }

    @Test
    public void toModel_Ok() {
        Product actual = productMapper.toModel(productRequestDto);
        Assertions.assertEquals("Engine", actual.getName());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getProductPrice());
    }
}
