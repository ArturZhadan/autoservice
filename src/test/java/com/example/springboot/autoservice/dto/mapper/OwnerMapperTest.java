package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.OwnerRequestDto;
import com.example.springboot.autoservice.dto.response.OwnerResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.OrderService;
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
class OwnerMapperTest {
    @InjectMocks
    private OwnerMapper ownerMapper;
    @Mock
    private CarService carService;
    @Mock
    private OrderService orderService;
    private Owner owner;
    private OwnerRequestDto ownerRequestDto;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        Car firstCar = new Car();
        firstCar.setId(4L);
        Car secondCar = new Car();
        secondCar.setId(8L);
        owner.setCars(List.of(firstCar, secondCar));
        Order firstOrder = new Order();
        firstOrder.setId(6L);
        Order secondOrder = new Order();
        secondOrder.setId(10L);
        owner.setOrders(List.of(firstOrder, secondOrder));
        ownerRequestDto = new OwnerRequestDto();
        ownerRequestDto.setCarsIds(List.of(4L, 8L));
        ownerRequestDto.setOrdersIds(List.of(6L, 10L));
    }

    @Test
    public void toDto_Ok() {
        OwnerResponseDto actual = ownerMapper.toDto(owner);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(List.of(4L, 8L), actual.getCarsIds());
        Assertions.assertEquals(List.of(6L, 10L), actual.getOrdersIds());
    }

    @Test
    public void toModel_Ok() {
        Car firstCar = new Car();
        firstCar.setId(4L);
        Car secondCar = new Car();
        secondCar.setId(8L);
        Mockito.when(carService.findAllById(ownerRequestDto.getCarsIds()))
                .thenReturn(List.of(firstCar, secondCar));
        Order firstOrder = new Order();
        firstOrder.setId(6L);
        Order secondOrder = new Order();
        secondOrder.setId(10L);
        Mockito.when(orderService.findAllById(ownerRequestDto.getOrdersIds()))
                .thenReturn(List.of(firstOrder, secondOrder));
        Owner actual = ownerMapper.toModel(ownerRequestDto);
        Assertions.assertEquals(List.of(4L, 8L),
                actual.getCars().stream().map(Car::getId).collect(Collectors.toList()));
        Assertions.assertEquals(List.of(6L, 10L),
                actual.getOrders().stream().map(Order::getId).collect(Collectors.toList()));
    }
}
