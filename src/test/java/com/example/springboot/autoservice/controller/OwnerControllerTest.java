package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.request.OwnerRequestDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.OwnerService;
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
class OwnerControllerTest {
    @MockBean
    private OwnerService ownerService;
    @MockBean
    private CarService carService;
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Owner owner = new Owner();
        Car firstCar = new Car();
        firstCar.setId(4L);
        Car secondCar = new Car();
        secondCar.setId(8L);
        owner.setCars(List.of(firstCar, secondCar));
        Mockito.when(carService.findAllById(List.of(4L, 8L)))
                .thenReturn(List.of(firstCar, secondCar));
        Order firstOrder = new Order();
        firstOrder.setId(6L);
        Order secondOrder = new Order();
        secondOrder.setId(10L);
        owner.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(6L, 10L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Owner savedOwner = new Owner();
        savedOwner.setId(1L);
        Car savedFirstCar = new Car();
        savedFirstCar.setId(4L);
        Car savedSecondCar = new Car();
        savedSecondCar.setId(8L);
        savedOwner.setCars(List.of(savedFirstCar, savedSecondCar));
        Order savedFirstOrder = new Order();
        savedFirstOrder.setId(6L);
        Order savedSecondOrder = new Order();
        savedSecondOrder.setId(10L);
        savedOwner.setOrders(List.of(savedFirstOrder, savedSecondOrder));
        Mockito.when(ownerService.save(owner)).thenReturn(savedOwner);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OwnerRequestDto(
                        owner.getCars().stream().map(Car::getId).collect(Collectors.toList()),
                        owner.getOrders().stream().map(Order::getId).collect(Collectors.toList())))
                .when()
                .post("/owners")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("carsIds", Matchers.equalTo(List.of(4, 8)))
                .body("ordersIds", Matchers.equalTo(List.of(6, 10)));
    }

    @Test
    public void update_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
        Car firstCar = new Car();
        firstCar.setId(4L);
        Car secondCar = new Car();
        secondCar.setId(8L);
        owner.setCars(List.of(firstCar, secondCar));
        Mockito.when(carService.findAllById(List.of(4L, 8L)))
                .thenReturn(List.of(firstCar, secondCar));
        Order firstOrder = new Order();
        firstOrder.setId(6L);
        Order secondOrder = new Order();
        secondOrder.setId(10L);
        owner.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(6L, 10L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Owner updatedOwner = new Owner();
        updatedOwner.setId(1L);
        Car updatedFirstCar = new Car();
        updatedFirstCar.setId(5L);
        Car updatedSecondCar = new Car();
        updatedSecondCar.setId(9L);
        updatedOwner.setCars(List.of(updatedFirstCar, updatedSecondCar));
        Mockito.when(carService.findAllById(List.of(5L, 9L)))
                .thenReturn(List.of(updatedFirstCar, updatedSecondCar));
        Order updatedFirstOrder = new Order();
        updatedFirstOrder.setId(7L);
        Order updatedSecondOrder = new Order();
        updatedSecondOrder.setId(11L);
        updatedOwner.setOrders(List.of(updatedFirstOrder, updatedSecondOrder));
        Mockito.when(orderService.findAllById(List.of(7L, 11L)))
                .thenReturn(List.of(updatedFirstOrder, updatedSecondOrder));
        Mockito.when(ownerService.save(owner)).thenReturn(updatedOwner);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OwnerRequestDto(
                        updatedOwner.getCars().stream().map(Car::getId).collect(Collectors.toList()),
                        updatedOwner.getOrders().stream().map(Order::getId).collect(Collectors.toList())))
                .when()
                .put("/owners/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("carsIds", Matchers.equalTo(List.of(5, 9)))
                .body("ordersIds", Matchers.equalTo(List.of(7, 11)));
    }

    @Test
    public void findAllOrdersByOwnerId_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
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
        owner.setOrders(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllById(List.of(6L, 10L)))
                .thenReturn(List.of(firstOrder, secondOrder));
        Mockito.when(orderService.findAllOrdersByOwnerId(1L)).thenReturn(List.of(firstOrder, secondOrder));
        RestAssuredMockMvc.when()
                .get("/owners/1/orders")
                .then()
                .status(HttpStatus.OK)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(6))
                .body("[1].id", Matchers.equalTo(10));
    }
}
