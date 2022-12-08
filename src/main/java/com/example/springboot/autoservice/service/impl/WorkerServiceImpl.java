package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.repository.WorkerRepository;
import com.example.springboot.autoservice.service.WorkerService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker update(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker findById(Long id) {
        return workerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find worker by id " + id));
    }

    @Override
    public List<Order> findAllOrdersByWorkerId(Long id) {
        return findById(id).getOrders();
    }

    @Override
    public BigDecimal getSalary(Long id) {
        List<Order> ordersByWorkerId = findAllOrdersByWorkerId(id);
        BigDecimal salary = BigDecimal.ZERO;
        for (Order order : ordersByWorkerId) {
            List<Proposal> proposals = order.getProposals();
            for (Proposal proposal : proposals) {
                if (proposal.getProposalStatus().equals(ProposalStatus.NOT_PAID)) {
                    salary = salary.add(proposal.getProposalPrice());
                    proposal.setProposalStatus(ProposalStatus.PAID);
                }
            }
        }
        return salary.multiply(BigDecimal.valueOf(0.4));
    }
}
