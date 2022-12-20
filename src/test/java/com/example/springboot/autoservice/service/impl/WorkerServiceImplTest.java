package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.ProposalService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorkerServiceImplTest {
    @InjectMocks
    private WorkerServiceImpl workerService;
    @Mock
    private ProposalService proposalService;

    @Test
    public void getSalary_Ok() {
        Worker worker = new Worker();
        worker.setId(1L);
        Proposal firstProposal = new Proposal();
        firstProposal.setProposalPrice(BigDecimal.valueOf(1000));
        firstProposal.setProposalStatus(ProposalStatus.NOT_PAID);
        Proposal secondProposal = new Proposal();
        secondProposal.setProposalPrice(BigDecimal.valueOf(2000));
        secondProposal.setProposalStatus(ProposalStatus.NOT_PAID);
        Mockito.when(proposalService.findAllProposalsByWorkerId(1L))
                .thenReturn(List.of(firstProposal, secondProposal));
        BigDecimal salary = workerService.getSalary(worker.getId());
        Assertions.assertEquals(BigDecimal.valueOf(1200.0), salary);
    }
}