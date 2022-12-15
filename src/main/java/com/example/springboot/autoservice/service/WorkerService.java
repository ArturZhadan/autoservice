package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Worker;
import java.math.BigDecimal;

public interface WorkerService {
    Worker save(Worker worker);

    Worker update(Worker worker);

    Worker findById(Long id);

    BigDecimal getSalary(Long id);
}
