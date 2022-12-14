package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.WorkerRequestDto;
import com.example.springboot.autoservice.dto.response.WorkerResponseDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.OrderService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkerMapper {
    private final OrderService orderService;

    public WorkerResponseDto toDto(Worker worker) {
        WorkerResponseDto dto = new WorkerResponseDto();
        dto.setId(worker.getId());
        dto.setFirstName(worker.getFirstName());
        dto.setLastName(worker.getLastName());
        dto.setOrderIds(worker.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Worker toModel(WorkerRequestDto dto) {
        Worker model = new Worker();
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setOrders(orderService.findAllById(dto.getOrderIds()));
        return model;
    }
}
