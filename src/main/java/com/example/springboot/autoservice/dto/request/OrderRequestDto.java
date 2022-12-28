package com.example.springboot.autoservice.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long carId;
    private String description;
    private LocalDate acceptanceDate;
    private List<Long> proposalsIds;
    private List<Long> productsIds;
    private String orderStatus;
    private BigDecimal orderPrice;
    private LocalDate completionDate;
}
