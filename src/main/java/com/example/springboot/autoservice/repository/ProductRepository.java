package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Order o join o.products p where o.id = :id")
    List<Product> findAllProductsByOrderId(Long id);
}
