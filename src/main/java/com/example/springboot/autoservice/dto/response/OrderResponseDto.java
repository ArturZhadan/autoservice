package com.example.springboot.autoservice.dto.response;

import com.example.springboot.autoservice.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate acceptanceDate;
    private List<Long> proposalsIds;
    private List<Long> productsIds;
    private OrderStatus orderStatus;
    private BigDecimal orderPrice;
    private LocalDate completionDate;
}
