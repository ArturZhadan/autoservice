package com.example.springboot.autoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusRequestDto {
    private String operation;
    private String key;
    private String value;
}
