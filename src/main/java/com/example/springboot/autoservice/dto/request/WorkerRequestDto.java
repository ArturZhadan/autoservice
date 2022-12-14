package com.example.springboot.autoservice.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerRequestDto {
    private String firstName;
    private String lastName;
    private List<Long> orderIds;
}