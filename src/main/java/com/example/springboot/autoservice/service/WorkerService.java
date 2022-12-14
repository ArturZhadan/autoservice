package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.Worker;
import java.math.BigDecimal;
import java.util.List;

public interface WorkerService {
    Worker save(Worker worker);

    Worker update(Worker worker);

    Worker findById(Long id);

    List<Order> findAllOrdersByWorkerId(Long id);

    List<Proposal> findAllProposalsByWorkerId(Long id);

    BigDecimal getSalary(Long id);
}
