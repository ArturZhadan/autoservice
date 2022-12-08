package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.OrderResponseDto;
import com.example.springboot.autoservice.dto.WorkerRequestDto;
import com.example.springboot.autoservice.dto.WorkerResponseDto;
import com.example.springboot.autoservice.dto.mapper.OrderMapper;
import com.example.springboot.autoservice.dto.mapper.WorkerMapper;
import com.example.springboot.autoservice.model.Worker;
import com.example.springboot.autoservice.service.WorkerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;
    private final WorkerMapper workerMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "save a new worker")
    public WorkerResponseDto save(@RequestBody WorkerRequestDto dto) {
        Worker worker = workerService.save(workerMapper.toModel(dto));
        return workerMapper.toDto(worker);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update worker by id")
    public WorkerResponseDto update(@PathVariable @ApiParam(value = "worker id") Long id,
                                    @RequestBody WorkerRequestDto dto) {
        Worker worker = workerMapper.toModel(dto);
        worker.setId(id);
        workerService.update(worker);
        return workerMapper.toDto(worker);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "get orders list by worker id")
    public List<OrderResponseDto> findAllOrdersByWorkerId(@PathVariable
                                                        @ApiParam(value = "worker id") Long id) {
        return workerService.findAllOrdersByWorkerId(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "get worker salary by id")
    public BigDecimal getSalary(@PathVariable @ApiParam(value = "worker id") Long id) {
        return workerService.getSalary(id);
    }
}
