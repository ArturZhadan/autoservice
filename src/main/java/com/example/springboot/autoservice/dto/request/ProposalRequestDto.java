package com.example.springboot.autoservice.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalRequestDto {
    private Long orderId;
    private Long workerId;
    private BigDecimal proposalPrice;
    private String proposalStatus;
}
