package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.repository.ProductRepository;
import com.example.springboot.autoservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find product by id " + id));
    }
}
