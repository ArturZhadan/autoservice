package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.1")
            .withDatabaseName("database")
            .withUsername("username")
            .withPassword("password");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        propertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql("/scripts/init_one_owner_two_orders.sql")
    public void findAllOrdersByOwnerId_Ok() {
        List<Order> actual = orderRepository.findAllOrdersByOwnerId(1L);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("description1", actual.get(0).getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-10"), actual.get(0).getAcceptanceDate());
        Assertions.assertEquals(OrderStatus.PAID, actual.get(0).getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(2500).setScale(2, RoundingMode.HALF_UP),
                actual.get(0).getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-20"), actual.get(0).getCompletionDate());
        Assertions.assertEquals("description2", actual.get(1).getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-15"), actual.get(1).getAcceptanceDate());
        Assertions.assertEquals(OrderStatus.PAID, actual.get(1).getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(3000).setScale(2, RoundingMode.HALF_UP),
                actual.get(1).getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-25"), actual.get(1).getCompletionDate());
    }

    @Test
    @Sql("/scripts/init_one_worker_two_orders.sql")
    public void findAllOrdersByWorkerId_Ok() {
        List<Order> actual = orderRepository.findAllOrdersByWorkerId(1L);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("description1", actual.get(0).getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-10"), actual.get(0).getAcceptanceDate());
        Assertions.assertEquals(OrderStatus.PAID, actual.get(0).getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(2500).setScale(2, RoundingMode.HALF_UP),
                actual.get(0).getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-20"), actual.get(0).getCompletionDate());
        Assertions.assertEquals("description2", actual.get(1).getDescription());
        Assertions.assertEquals(LocalDate.parse("2022-12-15"), actual.get(1).getAcceptanceDate());
        Assertions.assertEquals(OrderStatus.PAID, actual.get(1).getOrderStatus());
        Assertions.assertEquals(BigDecimal.valueOf(3000).setScale(2, RoundingMode.HALF_UP),
                actual.get(1).getOrderPrice());
        Assertions.assertEquals(LocalDate.parse("2022-12-25"), actual.get(1).getCompletionDate());
    }
}