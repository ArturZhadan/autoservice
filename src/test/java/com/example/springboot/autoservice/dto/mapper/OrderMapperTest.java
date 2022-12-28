package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.OrderRequestDto;
import com.example.springboot.autoservice.dto.response.OrderResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.ProductService;
import com.example.springboot.autoservice.service.ProposalService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {
    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private CarService carService;
    @Mock
    private ProposalService proposalService;
    @Mock
    private ProductService productService;
    private Order order;
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        Car car = new Car();
        car.setId(1L);
        order.setCar(car);
        order.setDescription("Description");
        order.setAcceptanceDate(LocalDate.parse("2022-12-10"));
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
        order.setOrderPrice(BigDecimal.valueOf(1000));
        order.setCompletionDate(LocalDate.parse("2022-12-20"));
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setCarId(1L);
        orderRequestDto.setDescription("Description");
        orderRequestDto.setAcceptanceDate(LocalDate.parse("2022-12-10"));
        orderRequestDto.setProposalsIds(List.of(1L, 2L));
        orderRequestDto.setProductsIds(List.of(1L, 2L));
        orderRequestDto.setOrderStatus(String.valueOf(OrderStatus.IN_PROCESS));
        orderRequestDto.setOrderPrice(BigDecimal.valueOf(1000));
        orderRequestDto.setCompletionDate(LocalDate.parse("2022-12-20"));
    }

    @Test
    public void toDto_Ok() {
        OrderResponseDto actual = orderMapper.toDto(order);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(1L, actual.getCarId());
        Assertions.assertEquals("Description", actual.getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-10"), actual.getAcceptanceDate());
        Assertions.assertEquals(List.of(3L, 6L), actual.getProposalsIds());
        Assertions.assertEquals(List.of(4L, 5L), actual.getProductsIds());
        Assertions.assertEquals(OrderStatus.IN_PROCESS, actual.getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-20"), actual.getCompletionDate());
    }

    @Test
    public void toModel_Ok() {
        Car car = new Car();
        car.setId(1L);
        Mockito.when(carService.findById(orderRequestDto.getCarId())).thenReturn(car);
        Proposal firstProposal = new Proposal();
        firstProposal.setId(3L);
        Proposal secondProposal = new Proposal();
        secondProposal.setId(6L);
        Mockito.when(proposalService.findAllById(orderRequestDto.getProposalsIds()))
                .thenReturn(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setId(4L);
        Product secondProduct = new Product();
        secondProduct.setId(5L);
        Mockito.when(productService.findAllById(orderRequestDto.getProductsIds()))
                .thenReturn(List.of(firstProduct, secondProduct));
        Order actual = orderMapper.toModel(orderRequestDto);
        Assertions.assertEquals("Description", actual.getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-10"), actual.getAcceptanceDate());
        Assertions.assertEquals(List.of(3L, 6L),
                actual.getProposals().stream().map(Proposal::getId).collect(Collectors.toList()));
        Assertions.assertEquals(List.of(4L, 5L),
                actual.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        Assertions.assertEquals(OrderStatus.IN_PROCESS, actual.getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(1000), actual.getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-20"), actual.getCompletionDate());
    }
}
