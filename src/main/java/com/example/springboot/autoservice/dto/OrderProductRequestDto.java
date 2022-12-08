package com.example.springboot.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequestDto {
    private String operation;
    private String key;
    private List<Long> productsIds;
}
