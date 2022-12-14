package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.mapper.CarMapper;
import com.example.springboot.autoservice.dto.request.CarRequestDto;
import com.example.springboot.autoservice.dto.response.CarResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping
    @ApiOperation(value = "save a new car")
    public CarResponseDto save(@RequestBody CarRequestDto dto) {
        Car car = carService.save(carMapper.toModel(dto));
        return carMapper.toDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update car by id")
    public CarResponseDto update(@PathVariable @ApiParam(value = "car id") Long id,
                                 @RequestBody CarRequestDto dto) {
        Car car = carMapper.toModel(dto);
        car.setId(id);
        carService.update(car);
        return carMapper.toDto(car);
    }
}
