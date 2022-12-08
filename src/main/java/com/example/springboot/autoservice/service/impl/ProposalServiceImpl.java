package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.repository.ProposalRepository;
import com.example.springboot.autoservice.service.ProposalService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;

    @Override
    public Proposal save(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal update(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public void updateProposalStatus(Long id, String key, String value) {
        Optional<Proposal> optionalProposal = proposalRepository.findById(id);
        if (optionalProposal.isPresent()) {
            Proposal proposal = optionalProposal.get();
            if (key.equalsIgnoreCase("proposalStatus")) {
                proposal.setProposalStatus(ProposalStatus.valueOf(value));
            }
            proposalRepository.save(proposal);
        }
    }

    @Override
    public Proposal findById(Long id) {
        return proposalRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find proposal by id" + id));
    }

    @Override
    public List<Proposal> findAllById(List<Long> ids) {
        return proposalRepository.findAllById(ids);
    }
}
