package com.example.springboot.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    private List<Long> carsIds;
    private List<Long> ordersIds;
}