package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.repository.OrderRepository;
import com.example.springboot.autoservice.service.ProductService;
import com.example.springboot.autoservice.service.ProposalService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductService productService;
    @Mock
    private ProposalService proposalService;

    @Test
    public void updateOrderStatus_Ok() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        order.setCompletionDate(LocalDate.parse("2022-12-15"));
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        orderService.updateOrderStatus(1L, "COMPLETED_SUCCESSFULLY");
        Assertions.assertEquals(OrderStatus.COMPLETED_SUCCESSFULLY, order.getOrderStatus());
        Assertions.assertEquals(LocalDate.now(), order.getCompletionDate());
    }

    @Test
    public void updateProductsList_Ok() {
        Order order = new Order();
        order.setId(1L);
        Product firstProduct = new Product();
        firstProduct.setId(5L);
        Product secondProduct = new Product();
        secondProduct.setId(6L);
        List<Product> list = new ArrayList<>();
        list.add(firstProduct);
        list.add(secondProduct);
        order.setProducts(list);
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Product thirdProduct = new Product();
        thirdProduct.setId(7L);
        Product fourthProduct = new Product();
        fourthProduct.setId(8L);
        Mockito.when(productService.findAllById(List.of(7L, 8L))).thenReturn(List.of(thirdProduct, fourthProduct));
        orderService.updateProductsList(1L, List.of(7L, 8L));
        Assertions.assertEquals(List.of(5L, 6L, 7L, 8L),
                order.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
    }

    @Test
    public void getPrice_Ok() {
        Order order = new Order();
        order.setId(1L);
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Proposal firstProposal = new Proposal();
        firstProposal.setProposalPrice(BigDecimal.valueOf(1200));
        Proposal secondProposal = new Proposal();
        secondProposal.setProposalPrice(BigDecimal.valueOf(1500));
        order.setProposals(List.of(firstProposal, secondProposal));
        Mockito.when(proposalService.findAllProposalsByOrderId(1L)).thenReturn(List.of(firstProposal, secondProposal));
        Product firstProduct = new Product();
        firstProduct.setProductPrice(BigDecimal.valueOf(500));
        Product secondProduct = new Product();
        secondProduct.setProductPrice(BigDecimal.valueOf(800));
        order.setProducts(List.of(firstProduct, secondProduct));
        Mockito.when(productService.findAllProductsByOrderId(1L)).thenReturn(List.of(firstProduct, secondProduct));
        BigDecimal price = orderService.getPrice(1L);
        Assertions.assertEquals(BigDecimal.valueOf(3999.94), price);
    }
}