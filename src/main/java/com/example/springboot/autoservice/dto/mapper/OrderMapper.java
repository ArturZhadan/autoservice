package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.OrderRequestDto;
import com.example.springboot.autoservice.dto.response.OrderResponseDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.ProductService;
import com.example.springboot.autoservice.service.ProposalService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final CarService carService;
    private final ProposalService proposalService;
    private final ProductService productService;

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCarId(order.getCar().getId());
        dto.setDescription(order.getDescription());
        dto.setAcceptanceDate(order.getAcceptanceDate());
        dto.setProposalsIds(order.getProposals()
                .stream()
                .map(Proposal::getId)
                .collect(Collectors.toList()));
        dto.setProductsIds(order.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        dto.setOrderStatus(order.getOrderStatus());
        dto.setOrderPrice(order.getOrderPrice());
        dto.setCompletionDate(order.getCompletionDate());
        return dto;
    }

    public Order toModel(OrderRequestDto dto) {
        Order model = new Order();
        model.setCar(carService.findById(dto.getCarId()));
        model.setDescription(dto.getDescription());
        model.setAcceptanceDate(dto.getAcceptanceDate());
        model.setProposals(proposalService.findAllById(dto.getProposalsIds()));
        model.setProducts(productService.findAllById(dto.getProductsIds()));
        model.setOrderStatus(OrderStatus.valueOf(dto.getOrderStatus().toUpperCase()));
        model.setOrderPrice(dto.getOrderPrice());
        model.setCompletionDate(dto.getCompletionDate());
        return model;
    }
}
