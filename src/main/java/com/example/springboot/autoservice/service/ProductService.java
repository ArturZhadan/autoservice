package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    List<Product> findAllById(List<Long> ids);

    Product findById(Long id);

    List<Product> findAllProductsByOrderId(Long id);
}
