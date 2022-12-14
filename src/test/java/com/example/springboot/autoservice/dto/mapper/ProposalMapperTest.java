package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.ProposalRequestDto;
import com.example.springboot.autoservice.dto.response.ProposalResponseDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.WorkerService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProposalMapperTest {
    @InjectMocks
    private ProposalMapper proposalMapper;
    @Mock
    private WorkerService workerService;
    @Mock
    private OrderService orderService;
    private Proposal proposal;
    private ProposalRequestDto proposalRequestDto;

    @BeforeEach
    void setUp() {
        proposal = new Proposal();
        proposal.setId(1L);
        Order order = new Order();
        order.setId(2L);
        proposal.setOrder(order);
        Worker worker = new Worker();
        worker.setId(3L);
        proposal.setWorker(worker);
        proposal.setProposalPrice(BigDecimal.valueOf(1000));
        proposal.setProposalStatus(ProposalStatus.PAID);
        proposalRequestDto = new ProposalRequestDto();
        proposalRequestDto.setOrderId(2L);
        proposalRequestDto.setWorkerId(3L);
        proposalRequestDto.setProposalPrice(BigDecimal.valueOf(1000));
        proposalRequestDto.setProposalStatus(String.valueOf(ProposalStatus.PAID));
    }

    @Test
    public void toDto_Ok() {
        ProposalResponseDto actual = proposalMapper.toDto(proposal);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(2L, actual.getOrderId());
        Assertions.assertEquals(3L, actual.getWorkerId());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getProposalPrice());
        Assertions.assertEquals(ProposalStatus.PAID, actual.getProposalStatus());
    }

    @Test
    public void toModel_Ok() {
        Order order = new Order();
        order.setId(2L);
        Mockito.when(orderService.findById(proposalRequestDto.getOrderId())).thenReturn(order);
        Worker worker = new Worker();
        worker.setId(3L);
        Mockito.when(workerService.findById(proposalRequestDto.getWorkerId())).thenReturn(worker);
        Proposal actual = proposalMapper.toModel(proposalRequestDto);
        Assertions.assertEquals(2L, actual.getOrder().getId());
        Assertions.assertEquals(3L, actual.getWorker().getId());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getProposalPrice());
        Assertions.assertEquals(ProposalStatus.PAID, actual.getProposalStatus());
    }
}
