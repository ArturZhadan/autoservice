package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.request.ProposalRequestDto;
import com.example.springboot.autoservice.dto.request.ProposalStatusRequestDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.ProposalService;
import com.example.springboot.autoservice.service.WorkerService;
import java.math.BigDecimal;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProposalControllerTest {
    @MockBean
    private ProposalService proposalService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private WorkerService workerService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Proposal proposal = new Proposal();
        Order order = new Order();
        order.setId(3L);
        proposal.setOrder(order);
        Mockito.when(orderService.findById(3L)).thenReturn(order);
        Worker worker = new Worker();
        worker.setId(4L);
        proposal.setWorker(worker);
        Mockito.when(workerService.findById(4L)).thenReturn(worker);
        proposal.setProposalPrice(BigDecimal.valueOf(1500));
        proposal.setProposalStatus(ProposalStatus.PAID);
        Proposal savedProposal = new Proposal();
        savedProposal.setId(1L);
        Order savedOrder = new Order();
        savedOrder.setId(3L);
        savedProposal.setOrder(savedOrder);
        Worker savedWorker = new Worker();
        savedWorker.setId(4L);
        savedProposal.setWorker(savedWorker);
        savedProposal.setProposalPrice(BigDecimal.valueOf(1500));
        savedProposal.setProposalStatus(ProposalStatus.PAID);
        Mockito.when(proposalService.save(proposal)).thenReturn(savedProposal);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProposalRequestDto(proposal.getOrder().getId(), proposal.getWorker().getId(),
                        proposal.getProposalPrice(), String.valueOf(proposal.getProposalStatus())))
                .when()
                .post("/proposals")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("orderId", Matchers.equalTo(3))
                .body("workerId", Matchers.equalTo(4))
                .body("proposalPrice", Matchers.equalTo(1500))
                .body("proposalStatus", Matchers.equalTo("PAID"));
    }

    @Test
    public void update_Ok() {
        Proposal proposal = new Proposal();
        proposal.setId(1L);
        Order order = new Order();
        order.setId(3L);
        proposal.setOrder(order);
        Mockito.when(orderService.findById(3L)).thenReturn(order);
        Worker worker = new Worker();
        worker.setId(4L);
        proposal.setWorker(worker);
        Mockito.when(workerService.findById(4L)).thenReturn(worker);
        proposal.setProposalPrice(BigDecimal.valueOf(1500));
        proposal.setProposalStatus(ProposalStatus.NOT_PAID);
        Proposal updatedProposal = new Proposal();
        updatedProposal.setId(1L);
        Order updatedOrder = new Order();
        updatedOrder.setId(5L);
        updatedProposal.setOrder(updatedOrder);
        Mockito.when(orderService.findById(5L)).thenReturn(updatedOrder);
        Worker updatedWorker = new Worker();
        updatedWorker.setId(6L);
        updatedProposal.setWorker(updatedWorker);
        Mockito.when(workerService.findById(6L)).thenReturn(updatedWorker);
        updatedProposal.setProposalPrice(BigDecimal.valueOf(2000));
        updatedProposal.setProposalStatus(ProposalStatus.PAID);
        Mockito.when(proposalService.update(proposal)).thenReturn(updatedProposal);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProposalRequestDto(updatedProposal.getOrder().getId(), updatedProposal.getWorker().getId(),
                        updatedProposal.getProposalPrice(), String.valueOf(updatedProposal.getProposalStatus())))
                .when()
                .put("/proposals/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("orderId", Matchers.equalTo(5))
                .body("workerId", Matchers.equalTo(6))
                .body("proposalPrice", Matchers.equalTo(2000))
                .body("proposalStatus", Matchers.equalTo("PAID"));
    }

    @Test
    public void updateProposalStatus_Ok() {
        Proposal proposal = new Proposal();
        proposal.setId(1L);
        Order order = new Order();
        order.setId(2L);
        proposal.setOrder(order);
        Worker worker = new Worker();
        worker.setId(3L);
        proposal.setWorker(worker);
        proposal.setProposalStatus(ProposalStatus.NOT_PAID);
        Proposal updatedProposal = new Proposal();
        updatedProposal.setId(1L);
        Mockito.when(proposalService.findById(1L)).thenReturn(updatedProposal);
        Order updatedOrder = new Order();
        updatedOrder.setId(2L);
        updatedProposal.setOrder(updatedOrder);
        Worker updatedWorker = new Worker();
        updatedWorker.setId(3L);
        updatedProposal.setWorker(updatedWorker);
        updatedProposal.setProposalStatus(ProposalStatus.PAID);
        Mockito.doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            String proposalStatus = invocation.getArgument(1);
            Assertions.assertEquals(1L, id);
            Assertions.assertEquals("PAID", proposalStatus);
            return null;
        }).when(proposalService).updateProposalStatus(anyLong(), anyString());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProposalStatusRequestDto(String.valueOf(updatedProposal.getProposalStatus())))
                .when()
                .patch("/proposals/1/status")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("proposalStatus", Matchers.equalTo("PAID"));
    }
}
