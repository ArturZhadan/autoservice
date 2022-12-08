package com.example.springboot.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerResponseDto {
    private Long id;
    private List<Long> carsIds;
    private List<Long> ordersIds;
}
