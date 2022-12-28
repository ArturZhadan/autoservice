package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.request.CarRequestDto;
import com.example.springboot.autoservice.model.Car;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.service.CarService;
import com.example.springboot.autoservice.service.OwnerService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @MockBean
    private CarService carService;
    @MockBean
    private OwnerService ownerService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Car car = new Car();
        car.setManufacturer("BMW");
        car.setModel("X5");
        car.setYear(2018);
        car.setNumber("AH 3333 KH");
        Owner owner = new Owner();
        owner.setId(2L);
        car.setOwner(owner);
        Mockito.when(ownerService.findById(2L)).thenReturn(owner);
        Car savedCar = new Car();
        savedCar.setId(1L);
        savedCar.setManufacturer("BMW");
        savedCar.setModel("X5");
        savedCar.setYear(2018);
        savedCar.setNumber("AH 3333 KH");
        Owner savedOwner = new Owner();
        savedOwner.setId(2L);
        savedCar.setOwner(savedOwner);
        Mockito.when(carService.save(car)).thenReturn(savedCar);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new CarRequestDto(car.getManufacturer(), car.getModel(), car.getYear(),
                        car.getNumber(), car.getOwner().getId()))
                .when()
                .post("/cars")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("manufacturer", Matchers.equalTo("BMW"))
                .body("model", Matchers.equalTo("X5"))
                .body("year", Matchers.equalTo(2018))
                .body("number", Matchers.equalTo("AH 3333 KH"))
                .body("ownerId", Matchers.equalTo(2));
    }

    @Test
    public void update_Ok() {
        Car car = new Car();
        car.setId(1L);
        car.setManufacturer("BMW");
        car.setModel("X5");
        car.setYear(2018);
        car.setNumber("AH 3333 KH");
        Owner owner = new Owner();
        owner.setId(2L);
        car.setOwner(owner);
        Mockito.when(ownerService.findById(2L)).thenReturn(owner);
        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setManufacturer("AUDI");
        updatedCar.setModel("Q5");
        updatedCar.setYear(2020);
        updatedCar.setNumber("AH 4444 KH");
        Owner updatedOwner = new Owner();
        updatedOwner.setId(2L);
        updatedCar.setOwner(updatedOwner);
        Mockito.when(carService.update(car)).thenReturn(updatedCar);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new CarRequestDto(updatedCar.getManufacturer(), updatedCar.getModel(),
                        updatedCar.getYear(), updatedCar.getNumber(), updatedCar.getOwner().getId()))
                .when()
                .put("/cars/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("manufacturer", Matchers.equalTo("AUDI"))
                .body("model", Matchers.equalTo("Q5"))
                .body("year", Matchers.equalTo(2020))
                .body("number", Matchers.equalTo("AH 4444 KH"))
                .body("ownerId", Matchers.equalTo(2));
    }
}
