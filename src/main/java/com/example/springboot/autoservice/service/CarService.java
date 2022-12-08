package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car update(Car car);

    List<Car> findAllById(List<Long> ids);

    Car findById(Long id);
}
