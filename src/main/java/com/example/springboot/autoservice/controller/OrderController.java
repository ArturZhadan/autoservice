package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.mapper.OrderMapper;
import com.example.springboot.autoservice.dto.request.OrderProductRequestDto;
import com.example.springboot.autoservice.dto.request.OrderRequestDto;
import com.example.springboot.autoservice.dto.request.OrderStatusRequestDto;
import com.example.springboot.autoservice.dto.response.OrderResponseDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "save a new order")
    public OrderResponseDto save(@RequestBody OrderRequestDto dto) {
        Order order = orderService.save(orderMapper.toModel(dto));
        return orderMapper.toDto(order);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update order by id")
    public OrderResponseDto update(@PathVariable @ApiParam(value = "order id") Long id,
                                   @RequestBody OrderRequestDto dto) {
        Order order = orderMapper.toModel(dto);
        order.setId(id);
        orderService.update(order);
        return orderMapper.toDto(order);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update order status by id")
    public OrderResponseDto updateOrderStatus(@PathVariable @ApiParam(value = "order id") Long id,
                                  @RequestBody OrderStatusRequestDto dto) {
        orderService.updateOrderStatus(id, dto.getOrderStatus());
        return orderMapper.toDto(orderService.findById(id));
    }

    @PatchMapping("/{id}/add-product")
    @ApiOperation(value = "add product to products list by order id")
    public OrderResponseDto updateProductsList(@PathVariable @ApiParam(value = "order id") Long id,
                                       @RequestBody OrderProductRequestDto dto) {
        orderService.updateProductsList(id, dto.getProductsIds());
        return orderMapper.toDto(orderService.findById(id));
    }

    @GetMapping("/{id}/price")
    @ApiOperation(value = "get order price by id")
    public BigDecimal getPrice(@PathVariable @ApiParam(value = "order id") Long id) {
        return orderService.getPrice(id);
    }
}
