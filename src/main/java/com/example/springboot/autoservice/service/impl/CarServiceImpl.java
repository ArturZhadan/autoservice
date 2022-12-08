package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.repository.CarRepository;
import com.example.springboot.autoservice.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAllById(List<Long> ids) {
        return carRepository.findAllById(ids);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find car by id " + id));
    }
}
