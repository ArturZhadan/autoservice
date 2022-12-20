package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.repository.ProposalRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProposalServiceImplTest {
    @InjectMocks
    private ProposalServiceImpl proposalService;
    @Mock
    private ProposalRepository proposalRepository;

    @Test
    public void updateProposalStatus_Ok() {
        Proposal proposal = new Proposal();
        proposal.setId(1L);
        proposal.setProposalStatus(ProposalStatus.NOT_PAID);
        Mockito.when(proposalRepository.findById(1L)).thenReturn(Optional.of(proposal));
        proposalService.updateProposalStatus(1L, "PAID");
        Assertions.assertEquals(ProposalStatus.PAID, proposal.getProposalStatus());
    }
}