package com.example.springboot.autoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private int year;
    private String number;
    private Long ownerId;
}
