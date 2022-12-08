package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.CarRequestDto;
import com.example.springboot.autoservice.dto.CarResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarMapper {
    private final OwnerService ownerService;

    public CarResponseDto toDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setId(car.getId());
        dto.setManufacturer(car.getManufacturer());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setNumber(car.getNumber());
        dto.setOwnerId(car.getOwner().getId());
        return dto;
    }

    public Car toModel(CarRequestDto dto) {
        Car model = new Car();
        model.setManufacturer(dto.getManufacturer());
        model.setModel(dto.getModel());
        model.setYear(dto.getYear());
        model.setNumber(dto.getNumber());
        model.setOwner(ownerService.findById(dto.getOwnerId()));
        return model;
    }
}
