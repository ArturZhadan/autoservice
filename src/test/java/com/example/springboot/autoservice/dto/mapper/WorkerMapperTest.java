package com.example.springboot.autoservice.dto.mapper;

import com.example.springboot.autoservice.dto.request.WorkerRequestDto;
import com.example.springboot.autoservice.dto.response.WorkerResponseDto;
import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Worker;
import java.util.List;
import java.util.stream.Collectors;
import com.example.springboot.autoservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorkerMapperTest {
    @InjectMocks
    private WorkerMapper workerMapper;
    @Mock
    private OrderService orderService;
    private Worker worker;
    private WorkerRequestDto workerRequestDto;


    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("John");
        worker.setLastName("Doe");
        Order firstOrder = new Order();
        firstOrder.setId(3L);
        Order secondOrder = new Order();
        secondOrder.setId(4L);
        worker.setOrders(List.of(firstOrder, secondOrder));
        workerRequestDto = new WorkerRequestDto();
        workerRequestDto.setFirstName("John");
        workerRequestDto.setLastName("Doe");
        workerRequestDto.setOrderIds(List.of(5L, 6L));
    }

    @Test
    public void toDto_Ok() {
        WorkerResponseDto actual = workerMapper.toDto(worker);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals("John", actual.getFirstName());
        Assertions.assertEquals("Doe", actual.getLastName());
        Assertions.assertEquals(List.of(3L, 4L), actual.getOrderIds());
    }

    @Test
    public void toModel_Ok() {
        Order firstOrder = new Order();
        firstOrder.setId(5L);
        Order secondOrder = new Order();
        secondOrder.setId(6L);
        Mockito.when(orderService.findAllById(workerRequestDto.getOrderIds()))
                .thenReturn(List.of(firstOrder, secondOrder));
        Worker actual = workerMapper.toModel(workerRequestDto);
        Assertions.assertEquals("John", actual.getFirstName());
        Assertions.assertEquals("Doe", actual.getLastName());
        Assertions.assertEquals(List.of(5L, 6L),
                actual.getOrders().stream().map(Order::getId).collect(Collectors.toList()));
    }
}
