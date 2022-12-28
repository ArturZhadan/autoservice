package com.example.springboot.autoservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private int year;
    private String number;
    private Long ownerId;
}
