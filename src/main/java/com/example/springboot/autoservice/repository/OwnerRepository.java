package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query(value = "select o from Owner ow join ow.orders o where ow.id = :id")
    List<Order> findAllOrdersByOwnerId(Long id);
}
