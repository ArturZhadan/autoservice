package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.OwnerRequestDto;
import com.example.springboot.autoservice.dto.OwnerResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.OrderService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final CarService carService;
    private final OrderService orderService;

    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setId(owner.getId());
        dto.setCarsIds(owner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        dto.setOrdersIds(owner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Owner toModel(OwnerRequestDto dto) {
        Owner model = new Owner();
        model.setCars(carService.findAllById(dto.getCarsIds()));
        model.setOrders(orderService.findAllById(dto.getOrdersIds()));
        return model;
    }
}
