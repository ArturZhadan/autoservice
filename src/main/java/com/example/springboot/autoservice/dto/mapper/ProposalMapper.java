package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.ProposalRequestDto;
import com.example.springboot.autoservice.dto.response.ProposalResponseDto;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProposalMapper {
    private final WorkerService workerService;
    private final OrderService orderService;

    public ProposalResponseDto toDto(Proposal proposal) {
        ProposalResponseDto dto = new ProposalResponseDto();
        dto.setId(proposal.getId());
        dto.setOrderId(proposal.getOrder().getId());
        dto.setWorkerId(proposal.getWorker().getId());
        dto.setProposalPrice(proposal.getProposalPrice());
        dto.setProposalStatus(proposal.getProposalStatus());
        return dto;
    }

    public Proposal toModel(ProposalRequestDto dto) {
        Proposal model = new Proposal();
        model.setOrder(orderService.findById(dto.getOrderId()));
        model.setWorker(workerService.findById(dto.getWorkerId()));
        model.setProposalPrice(dto.getProposalPrice());
        model.setProposalStatus(ProposalStatus.valueOf(dto.getProposalStatus().toUpperCase()));
        return model;
    }
}
