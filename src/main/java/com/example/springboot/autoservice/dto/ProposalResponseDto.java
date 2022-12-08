package com.example.springboot.autoservice.dto;

import com.example.springboot.autoservice.model.ProposalStatus;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponseDto {
    private Long id;
    private Long orderId;
    private Long workerId;
    private BigDecimal proposalPrice;
    private ProposalStatus proposalStatus;
}
