package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Proposal;
import java.util.List;

public interface ProposalService {
    Proposal save(Proposal proposal);

    Proposal update(Proposal proposal);

    void updateProposalStatus(Long id, String proposalStatus);

    Proposal findById(Long id);

    List<Proposal> findAllById(List<Long> ids);

    List<Proposal> findAllProposalsByOrderId(Long id);

    List<Proposal> findAllProposalsByWorkerId(Long id);
}
