package com.example.springboot.autoservice.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequestDto {
    private List<Long> productsIds;
}
