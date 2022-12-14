package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.mapper.ProductMapper;
import com.example.springboot.autoservice.dto.request.ProductRequestDto;
import com.example.springboot.autoservice.dto.response.ProductResponseDto;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @ApiOperation(value = "save a new product")
    public ProductResponseDto save(@RequestBody ProductRequestDto dto) {
        Product product = productService.save(productMapper.toModel(dto));
        return productMapper.toDto(product);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update product by id")
    public ProductResponseDto update(@PathVariable @ApiParam(value = "product id") Long id,
                                     @RequestBody ProductRequestDto dto) {
        Product product = productMapper.toModel(dto);
        product.setId(id);
        productService.update(product);
        return productMapper.toDto(product);
    }
}
