package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.request.WorkerRequestDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.ProposalService;
import com.example.springboot.autoservice.service.WorkerService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WorkerControllerTest {
    @MockBean
    private WorkerService workerService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ProposalService proposalService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Worker worker = new Worker();
        worker.setFirstName("John");
        worker.setLastName("Doe");
        Order firstOrder = new Order();
        firstOrder.setId(3L);
        Order secondOrder = new Order();
        secondOrder.setId(4L);
        worker.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(3L, 4L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Worker savedWorker = new Worker();
        savedWorker.setId(1L);
        savedWorker.setFirstName("John");
        savedWorker.setLastName("Doe");
        Order savedFirstOrder = new Order();
        savedFirstOrder.setId(3L);
        Order savedSecondOrder = new Order();
        savedSecondOrder.setId(4L);
        savedWorker.setOrders(List.of(savedFirstOrder, savedSecondOrder));
        Mockito.when(workerService.save(worker)).thenReturn(savedWorker);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new WorkerRequestDto(worker.getFirstName(), worker.getLastName(),
                        worker.getOrders().stream().map(Order::getId).collect(Collectors.toList())))
                .when()
                .post("/workers")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("firstName", Matchers.equalTo("John"))
                .body("lastName", Matchers.equalTo("Doe"))
                .body("orderIds", Matchers.equalTo(List.of(3, 4)));
    }

    @Test
    public void update_Ok() {
        Worker worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("John");
        worker.setLastName("Doe");
        Order firstOrder = new Order();
        firstOrder.setId(3L);
        Order secondOrder = new Order();
        secondOrder.setId(4L);
        worker.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(3L, 4L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Worker updatedWorker = new Worker();
        updatedWorker.setId(1L);
        updatedWorker.setFirstName("Bob");
        updatedWorker.setLastName("Smith");
        Order updatedFirstOrder = new Order();
        updatedFirstOrder.setId(5L);
        Order updatedSecondOrder = new Order();
        updatedSecondOrder.setId(6L);
        updatedWorker.setOrders(List.of(updatedFirstOrder, updatedSecondOrder));
        Mockito.when(orderService.findAllById(List.of(5L, 6L)))
                .thenReturn(List.of(updatedFirstOrder, updatedSecondOrder));
        Mockito.when(workerService.update(worker)).thenReturn(updatedWorker);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new WorkerRequestDto(updatedWorker.getFirstName(), updatedWorker.getLastName(),
                        updatedWorker.getOrders().stream().map(Order::getId).collect(Collectors.toList())))
                .when()
                .put("/workers/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("firstName", Matchers.equalTo("Bob"))
                .body("lastName", Matchers.equalTo("Smith"))
                .body("orderIds", Matchers.equalTo(List.of(5, 6)));
    }

    @Test
    public void findAllOrdersByWorkerId_Ok() {
        Worker worker = new Worker();
        worker.setId(1L);
        Order firstOrder = new Order();
        firstOrder.setId(6L);
        Car firstCar = new Car();
        firstCar.setId(7L);
        firstOrder.setCar(firstCar);
        Proposal firstProposal = new Proposal();
        firstProposal.setId(8L);
        firstOrder.setProposals(List.of(firstProposal));
        Product firstProduct = new Product();
        firstProduct.setId(9L);
        firstOrder.setProducts(List.of(firstProduct));
        Order secondOrder = new Order();
        secondOrder.setId(10L);
        Car secondCar = new Car();
        secondCar.setId(11L);
        secondOrder.setCar(secondCar);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(12L);
        secondOrder.setProposals(List.of(secondProposal));
        Product secondProduct = new Product();
        secondProduct.setId(13L);
        secondOrder.setProducts(List.of(secondProduct));
        worker.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(6L, 10L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllOrdersByWorkerId(1L)).thenReturn(List.of(firstOrder, secondOrder));
        RestAssuredMockMvc.when()
                .get("/workers/1/orders")
                .then()
                .status(HttpStatus.OK)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(6))
                .body("[1].id", Matchers.equalTo(10));
    }

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
        Mockito.when(workerService.getSalary(1L)).thenReturn(BigDecimal.valueOf(1200));
        RestAssuredMockMvc.when()
                .get("/workers/1/salary")
                .then()
                .status(HttpStatus.OK)
                .body(Matchers.equalTo("1200"));
    }
}
