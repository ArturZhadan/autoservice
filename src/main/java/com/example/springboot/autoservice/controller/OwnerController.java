package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.mapper.OrderMapper;
import com.example.springboot.autoservice.dto.mapper.OwnerMapper;
import com.example.springboot.autoservice.dto.request.OwnerRequestDto;
import com.example.springboot.autoservice.dto.response.OrderResponseDto;
import com.example.springboot.autoservice.dto.response.OwnerResponseDto;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OrderService orderService;
    private final OwnerMapper ownerMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "save a new owner")
    public OwnerResponseDto save(@RequestBody OwnerRequestDto dto) {
        Owner owner = ownerService.save(ownerMapper.toModel(dto));
        return ownerMapper.toDto(owner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update owner by id")
    public OwnerResponseDto update(@PathVariable @ApiParam(value = "owner id") Long id,
                                   @RequestBody OwnerRequestDto dto) {
        Owner owner = ownerMapper.toModel(dto);
        owner.setId(id);
        ownerService.update(owner);
        return ownerMapper.toDto(owner);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "get orders list by owner id")
    public List<OrderResponseDto> findAllOrdersByOwnerId(@PathVariable
                                                        @ApiParam(value = "owner id") Long id) {
        return orderService.findAllOrdersByOwnerId(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
