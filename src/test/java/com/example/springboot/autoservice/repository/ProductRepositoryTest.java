package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
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
    private ProductRepository productRepository;

    @Test
    @Sql("/scripts/init_one_order_three_products.sql")
    public void findAllProductsByOrderId_Ok() {
        List<Product> actual = productRepository.findAllProductsByOrderId(1L);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("engine", actual.get(0).getName());
        Assertions.assertEquals(BigDecimal.valueOf(2000).setScale(2, RoundingMode.HALF_UP),
                actual.get(0).getProductPrice());
        Assertions.assertEquals("wheel", actual.get(1).getName());
        Assertions.assertEquals(BigDecimal.valueOf(900.000).setScale(2, RoundingMode.HALF_UP),
                actual.get(1).getProductPrice());
        Assertions.assertEquals("oil", actual.get(2).getName());
        Assertions.assertEquals(BigDecimal.valueOf(250.000).setScale(2, RoundingMode.HALF_UP),
                actual.get(2).getProductPrice());
    }
}