package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.CarRequestDto;
import com.example.springboot.autoservice.dto.response.CarResponseDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.service.OwnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {
    @InjectMocks
    private CarMapper carMapper;
    @Mock
    private OwnerService ownerService;
    private Car car;
    private CarRequestDto carRequestDto;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setId(1L);
        car.setManufacturer("BMW");
        car.setModel("X5");
        car.setYear(2018);
        car.setNumber("AH 3333 KH");
        Owner owner = new Owner();
        owner.setId(1L);
        car.setOwner(owner);
        carRequestDto = new CarRequestDto();
        carRequestDto.setManufacturer("BMW");
        carRequestDto.setModel("X5");
        carRequestDto.setYear(2018);
        carRequestDto.setNumber("AH 3333 KH");
        carRequestDto.setOwnerId(1L);
    }

    @Test
    public void toDto_Ok() {
        CarResponseDto actual = carMapper.toDto(car);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals("BMW", actual.getManufacturer());
        Assertions.assertEquals("X5", actual.getModel());
        Assertions.assertEquals(2018, actual.getYear());
        Assertions.assertEquals("AH 3333 KH", actual.getNumber());
        Assertions.assertEquals(1L, actual.getOwnerId());
    }

    @Test
    public void toModel_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
        Mockito.when(ownerService.findById(carRequestDto.getOwnerId())).thenReturn(owner);
        Car actual = carMapper.toModel(carRequestDto);
        Assertions.assertEquals("BMW", actual.getManufacturer());
        Assertions.assertEquals("X5", actual.getModel());
        Assertions.assertEquals(2018, actual.getYear());
        Assertions.assertEquals("AH 3333 KH", actual.getNumber());
        Assertions.assertEquals(1L, actual.getOwner().getId());
    }
}
