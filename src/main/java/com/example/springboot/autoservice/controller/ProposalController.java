package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.mapper.ProposalMapper;
import com.example.springboot.autoservice.dto.request.ProposalRequestDto;
import com.example.springboot.autoservice.dto.request.ProposalStatusRequestDto;
import com.example.springboot.autoservice.dto.response.ProposalResponseDto;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.service.ProposalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;

    @PostMapping
    @ApiOperation(value = "save a new proposal")
    public ProposalResponseDto save(@RequestBody ProposalRequestDto dto) {
        Proposal proposal = proposalService.save(proposalMapper.toModel(dto));
        return proposalMapper.toDto(proposal);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update proposal by id")
    public ProposalResponseDto update(@PathVariable @ApiParam(value = "proposal id") Long id,
                                      @RequestBody ProposalRequestDto dto) {
        Proposal proposal = proposalMapper.toModel(dto);
        proposal.setId(id);
        proposalService.update(proposal);
        return proposalMapper.toDto(proposal);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update proposal status by id")
    public ProposalResponseDto updateProposalStatus(@PathVariable
                                                        @ApiParam(value = "proposal id") Long id,
                                     @RequestBody ProposalStatusRequestDto dto) {
        proposalService.updateProposalStatus(id, dto.getProposalStatus());
        return proposalMapper.toDto(proposalService.findById(id));
    }
}
