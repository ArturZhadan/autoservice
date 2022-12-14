package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.Worker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query(value = "select o from Worker w join w.orders o where w.id = :id")
    List<Order> findAllOrdersByWorkerId(Long id);

    @Query(value = "select p from Worker w join w.orders o join o.proposals p where w.id = :id")
    List<Proposal> findAllProposalsByWorkerId(Long id);
}
