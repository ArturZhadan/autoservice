package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select p from Order o join o.proposals p where o.id = :id")
    List<Proposal> findAllProposalsByOrderId(Long id);

    @Query(value = "select p from Order o join o.products p where o.id = :id")
    List<Product> findAllProductsByOrderId(Long id);
}
