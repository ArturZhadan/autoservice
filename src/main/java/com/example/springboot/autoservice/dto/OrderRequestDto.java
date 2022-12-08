package com.example.springboot.autoservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String description;
    private Date acceptanceDate;
    private List<Long> proposalsIds;
    private List<Long> productsIds;
    private String orderStatus;
    private BigDecimal orderPrice;
    private Date completionDate;
}
