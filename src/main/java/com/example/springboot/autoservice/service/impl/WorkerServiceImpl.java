package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.repository.WorkerRepository;
import com.example.springboot.autoservice.service.ProposalService;
import com.example.springboot.autoservice.service.WorkerService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private static final double WORKER_SALARY = 0.4;
    private final WorkerRepository workerRepository;
    private final ProposalService proposalService;

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
    public BigDecimal getSalary(Long id) {
        BigDecimal salary = BigDecimal.ZERO;
        List<Proposal> proposals = proposalService.findAllProposalsByWorkerId(id);
        for (Proposal proposal : proposals) {
            if (proposal.getProposalStatus().equals(ProposalStatus.NOT_PAID)) {
                salary = salary.add(proposal.getProposalPrice());
                proposal.setProposalStatus(ProposalStatus.PAID);
                proposalService.save(proposal);
            }
        }
        return salary.multiply(BigDecimal.valueOf(WORKER_SALARY));
    }
}
