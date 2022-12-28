package com.example.springboot.autoservice.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import com.example.springboot.autoservice.dto.request.OrderProductRequestDto;
import com.example.springboot.autoservice.dto.request.OrderRequestDto;
import com.example.springboot.autoservice.dto.request.OrderStatusRequestDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.ProductService;
import com.example.springboot.autoservice.service.ProposalService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @MockBean
    private OrderService orderService;
    @MockBean
    private CarService carService;
    @MockBean
    private ProposalService proposalService;
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Order order = new Order();
        Car car = new Car();
        car.setId(2L);
        order.setCar(car);
        Mockito.when(carService.findById(2L)).thenReturn(car);
        order.setDescription("Description");
        order.setAcceptanceDate(LocalDate.parse("2022-12-10"));
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        order.setProposals(List.of(firstProposal, secondProposal));
        Mockito.when(proposalService.findAllById(List.of(3L,6L)))
                .thenReturn(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        order.setProducts(List.of(firstProduct, secondProduct));
        Mockito.when(productService.findAllById(List.of(4L, 5L)))
                .thenReturn(List.of(firstProduct, secondProduct));
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        order.setOrderPrice(BigDecimal.valueOf(1000));
        order.setCompletionDate(LocalDate.parse("2022-12-20"));
        Order savedOrder = new Order();
        savedOrder.setId(1L);
        Car savedCar = new Car();
        savedCar.setId(2L);
        savedOrder.setCar(savedCar);
        savedOrder.setDescription("Description");
        savedOrder.setAcceptanceDate(LocalDate.parse("2022-12-10"));
        Proposal savedFirstProposal = new Proposal();
        savedFirstProposal.setId(3L);
        Proposal savedSecondProposal = new Proposal();
        savedSecondProposal.setId(6L);
        savedOrder.setProposals(List.of(savedFirstProposal, savedSecondProposal));
        Product savedFirstProduct = new Product();
        savedFirstProduct.setId(4L);
        Product savedSecondProduct = new Product();
        savedSecondProduct.setId(5L);
        savedOrder.setProducts(List.of(savedFirstProduct ,savedSecondProduct));
        savedOrder.setOrderStatus(OrderStatus.IN_PROCESS);
        savedOrder.setOrderPrice(BigDecimal.valueOf(1000));
        savedOrder.setCompletionDate(LocalDate.parse("2022-12-20"));
        Mockito.when(orderService.save(order)).thenReturn(savedOrder);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OrderRequestDto(order.getCar().getId(), order.getDescription(), order.getAcceptanceDate(),
                        order.getProposals().stream().map(Proposal::getId).collect(Collectors.toList()),
                        order.getProducts().stream().map(Product::getId).collect(Collectors.toList()),
                        String.valueOf(order.getOrderStatus()), order.getOrderPrice(), order.getCompletionDate()))
                .when()
                .post("/orders")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("carId", Matchers.equalTo(2))
                .body("description", Matchers.equalTo("Description"))
                .body("acceptanceDate", Matchers.equalTo("2022-12-10"))
                .body("proposalsIds", Matchers.equalTo(List.of(3, 6)))
                .body("productsIds", Matchers.equalTo(List.of(4, 5)))
                .body("orderStatus", Matchers.equalTo("IN_PROCESS"))
                .body("orderPrice", Matchers.equalTo(1000))
                .body("completionDate", Matchers.equalTo("2022-12-20"));
    }

    @Test
    public void update_Ok() {
        Order order = new Order();
        order.setId(1L);
        Car car = new Car();
        car.setId(2L);
        order.setCar(car);
        Mockito.when(carService.findById(2L)).thenReturn(car);
        order.setDescription("Description");
        order.setAcceptanceDate(LocalDate.parse("2022-12-10"));
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        order.setProposals(List.of(firstProposal, secondProposal));
        Mockito.when(proposalService.findAllById(List.of(3L,6L)))
                .thenReturn(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        order.setProducts(List.of(firstProduct, secondProduct));
        Mockito.when(productService.findAllById(List.of(4L, 5L)))
                .thenReturn(List.of(firstProduct, secondProduct));
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        order.setOrderPrice(BigDecimal.valueOf(1000));
        order.setCompletionDate(LocalDate.parse("2022-12-20"));
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        Car updatedCar = new Car();
        updatedCar.setId(2L);
        updatedOrder.setCar(updatedCar);
        updatedOrder.setDescription("Description");
        updatedOrder.setAcceptanceDate(LocalDate.parse("2022-12-11"));
        Proposal updatedFirstProposal = new Proposal();
        updatedFirstProposal.setId(3L);
        Proposal updatedSecondProposal = new Proposal();
        updatedSecondProposal.setId(6L);
        updatedOrder.setProposals(List.of(updatedFirstProposal, updatedSecondProposal));
        Product updatedFirstProduct = new Product();
        updatedFirstProduct.setId(4L);
        Product updatedSecondProduct = new Product();
        updatedSecondProduct.setId(5L);
        updatedOrder.setProducts(List.of(updatedFirstProduct ,updatedSecondProduct));
        updatedOrder.setOrderStatus(OrderStatus.IN_PROCESS);
        updatedOrder.setOrderPrice(BigDecimal.valueOf(1000));
        updatedOrder.setCompletionDate(LocalDate.parse("2022-12-21"));
        Mockito.when(orderService.update(order)).thenReturn(updatedOrder);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OrderRequestDto(updatedOrder.getCar().getId(), updatedOrder.getDescription(),
                        updatedOrder.getAcceptanceDate(),
                        updatedOrder.getProposals().stream().map(Proposal::getId).collect(Collectors.toList()),
                        updatedOrder.getProducts().stream().map(Product::getId).collect(Collectors.toList()),
                        String.valueOf(updatedOrder.getOrderStatus()), updatedOrder.getOrderPrice(),
                        updatedOrder.getCompletionDate()))
                .when()
                .put("/orders/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("carId", Matchers.equalTo(2))
                .body("description", Matchers.equalTo("Description"))
                .body("acceptanceDate", Matchers.equalTo("2022-12-11"))
                .body("proposalsIds", Matchers.equalTo(List.of(3, 6)))
                .body("productsIds", Matchers.equalTo(List.of(4, 5)))
                .body("orderStatus", Matchers.equalTo("IN_PROCESS"))
                .body("orderPrice", Matchers.equalTo(1000))
                .body("completionDate", Matchers.equalTo("2022-12-21"));
    }

    @Test
    public void updateOrderStatus_Ok() {
        Order order = new Order();
        order.setId(1L);
        Car car = new Car();
        car.setId(2L);
        order.setCar(car);
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        order.setProposals(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        order.setProducts(List.of(firstProduct, secondProduct));
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        Mockito.when(orderService.findById(1L)).thenReturn(updatedOrder);
        Car updatedCar = new Car();
        updatedCar.setId(2L);
        updatedOrder.setCar(updatedCar);
        Proposal updatedFirstProposal = new Proposal();
        updatedFirstProposal.setId(3L);
        Proposal updatedSecondProposal = new Proposal();
        updatedSecondProposal.setId(6L);
        updatedOrder.setProposals(List.of(updatedFirstProposal, updatedSecondProposal));
        Product updatedFirstProduct = new Product();
        updatedFirstProduct.setId(4L);
        Product updatedSecondProduct = new Product();
        updatedSecondProduct.setId(5L);
        updatedOrder.setProducts(List.of(updatedFirstProduct ,updatedSecondProduct));
        updatedOrder.setOrderStatus(OrderStatus.COMPLETED_SUCCESSFULLY);
        Mockito.doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            String orderStatus = invocation.getArgument(1);
            Assertions.assertEquals(1L, id);
            Assertions.assertEquals("COMPLETED_SUCCESSFULLY", orderStatus);
            return null;
        }).when(orderService).updateOrderStatus(anyLong(), anyString());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OrderStatusRequestDto(String.valueOf(updatedOrder.getOrderStatus())))
                .when()
                .patch("/orders/1/status")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("orderStatus", Matchers.equalTo("COMPLETED_SUCCESSFULLY"));
    }

    @Test
    public void updateProductsList_Ok() {
        Order order = new Order();
        Car car = new Car();
        car.setId(2L);
        order.setCar(car);
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        order.setProposals(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        order.setProducts(List.of(firstProduct, secondProduct));
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        Mockito.when(orderService.findById(1L)).thenReturn(updatedOrder);
        Car updatedCar = new Car();
        updatedCar.setId(2L);
        updatedOrder.setCar(updatedCar);
        Proposal updatedFirstProposal = new Proposal();
        updatedFirstProposal.setId(3L);
        Proposal updatedSecondProposal = new Proposal();
        updatedSecondProposal.setId(6L);
        updatedOrder.setProposals(List.of(updatedFirstProposal, updatedSecondProposal));
        Product updatedFirstProduct = new Product();
        updatedFirstProduct.setId(4L);
        Product updatedSecondProduct = new Product();
        updatedSecondProduct.setId(5L);
        Product updatedThirdProduct = new Product();
        updatedThirdProduct.setId(7L);
        updatedOrder.setProducts(List.of(updatedFirstProduct ,updatedSecondProduct, updatedThirdProduct));
        Mockito.doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            List<Long> productsIds = invocation.getArgument(1);
            Assertions.assertEquals(1L, id);
            Assertions.assertEquals(List.of(4L, 5L, 7L), productsIds);
            return null;
        }).when(orderService).updateProductsList(anyLong(), anyList());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OrderProductRequestDto(List.of(updatedFirstProduct.getId(),
                        updatedSecondProduct.getId(), updatedThirdProduct.getId())))
                .when()
                .patch("/orders/1/add-product")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("productsIds.size()", Matchers.equalTo(3))
                .body("productsIds", Matchers.equalTo(List.of(4, 5, 7)))
                .body("productsIds[0]", Matchers.equalTo(4))
                .body("productsIds[1]", Matchers.equalTo(5))
                .body("productsIds[2]", Matchers.equalTo(7));
   }

    @Test
    public void getPrice_Ok() {
        Order order = new Order();
        order.setId(1L);
        Car car = new Car();
        car.setId(2L);
        order.setCar(car);
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        firstProposal.setProposalPrice(BigDecimal.valueOf(2300));
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        secondProposal.setProposalPrice(BigDecimal.valueOf(3200));
        order.setProposals(List.of(firstProposal, secondProposal));
        Mockito.when(proposalService.findAllById(List.of(3L,6L)))
                .thenReturn(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        firstProduct.setProductPrice(BigDecimal.valueOf(700));
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        secondProduct.setProductPrice(BigDecimal.valueOf(1100));
        order.setProducts(List.of(firstProduct, secondProduct));
        Mockito.when(productService.findAllById(List.of(4L, 5L)))
                .thenReturn(List.of(firstProduct, secondProduct));
        Mockito.when(orderService.getPrice(1L)).thenReturn(BigDecimal.valueOf(7299.94));
        RestAssuredMockMvc.when()
                .get("/orders/1/price")
                .then()
                .status(HttpStatus.OK)
                .body(Matchers.equalTo("7299.94"));
    }
}
