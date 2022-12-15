package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Owner ow join ow.orders o where ow.id = :id")
    List<Order> findAllOrdersByOwnerId(Long id);

    @Query(value = "select o from Worker w join w.orders o where w.id = :id")
    List<Order> findAllOrdersByWorkerId(Long id);
}
