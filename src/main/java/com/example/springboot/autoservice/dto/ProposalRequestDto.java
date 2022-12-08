package com.example.springboot.autoservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalRequestDto {
    private Long orderId;
    private Long workerId;
    private BigDecimal proposalPrice;
    private String proposalStatus;
}
