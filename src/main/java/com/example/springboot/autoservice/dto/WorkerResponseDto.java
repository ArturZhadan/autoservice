package com.example.springboot.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Long> orderIds;
}
