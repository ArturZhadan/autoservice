package com.example.springboot.autoservice.dto;

import com.example.springboot.autoservice.model.OrderStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private Date acceptanceDate;
    private List<Long> proposalsIds;
    private List<Long> productsIds;
    private OrderStatus orderStatus;
    private BigDecimal orderPrice;
    private Date completionDate;
}
