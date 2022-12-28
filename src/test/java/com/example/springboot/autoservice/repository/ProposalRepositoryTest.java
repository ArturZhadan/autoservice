package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.model.ProposalStatus;
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
import java.util.List;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProposalRepositoryTest {
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
    private ProposalRepository proposalRepository;

    @Test
    @Sql("/scripts/init_one_order_three_proposals.sql")
    public void findAllProposalsByOrderId_Ok() {
        List<Proposal> actual = proposalRepository.findAllProposalsByOrderId(1L);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals(BigDecimal.valueOf(2700).setScale(2, RoundingMode.HALF_UP),
                actual.get(0).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.PAID, actual.get(0).getProposalStatus());
        Assertions.assertEquals(BigDecimal.valueOf(3100).setScale(2, RoundingMode.HALF_UP),
                actual.get(1).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.PAID, actual.get(1).getProposalStatus());
        Assertions.assertEquals(BigDecimal.valueOf(4200).setScale(2, RoundingMode.HALF_UP),
                actual.get(2).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.PAID, actual.get(2).getProposalStatus());
    }

    @Test
    @Sql("/scripts/init_one_worker_two_orders_four_proposals.sql")
    public void findAllProposalsByWorkerId_Ok() {
        List<Proposal> actual = proposalRepository.findAllProposalsByWorkerId(1L);
        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals(BigDecimal.valueOf(1800).setScale(2, RoundingMode.HALF_UP),
                actual.get(0).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.NOT_PAID, actual.get(0).getProposalStatus());
        Assertions.assertEquals(BigDecimal.valueOf(3500).setScale(2, RoundingMode.HALF_UP),
                actual.get(1).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.NOT_PAID, actual.get(1).getProposalStatus());
        Assertions.assertEquals(BigDecimal.valueOf(2200).setScale(2, RoundingMode.HALF_UP),
                actual.get(2).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.NOT_PAID, actual.get(2).getProposalStatus());
        Assertions.assertEquals(BigDecimal.valueOf(2800).setScale(2, RoundingMode.HALF_UP),
                actual.get(3).getProposalPrice());
        Assertions.assertEquals(ProposalStatus.NOT_PAID, actual.get(3).getProposalStatus());
    }
}