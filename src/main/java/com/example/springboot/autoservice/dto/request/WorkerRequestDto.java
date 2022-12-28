package com.example.springboot.autoservice.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequestDto {
    private String firstName;
    private String lastName;
    private List<Long> orderIds;
}
