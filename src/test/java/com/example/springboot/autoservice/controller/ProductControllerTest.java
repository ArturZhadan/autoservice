package com.example.springboot.autoservice.controller;

import com.example.springboot.autoservice.dto.request.ProductRequestDto;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.service.ProductService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void save_Ok() {
        Product product = new Product();
        product.setName("engine");
        product.setProductPrice(BigDecimal.valueOf(1000));
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("engine");
        savedProduct.setProductPrice(BigDecimal.valueOf(1000));
        Mockito.when(productService.save(product)).thenReturn(savedProduct);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProductRequestDto(product.getName(), product.getProductPrice()))
                .when()
                .post("/products")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("engine"))
                .body("productPrice", Matchers.equalTo(1000));
    }

    @Test
    void update_Ok() {
        Product product = new Product();
        product.setId(1L);
        product.setName("engine");
        product.setProductPrice(BigDecimal.valueOf(1000));
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("wheel");
        updatedProduct.setProductPrice(BigDecimal.valueOf(500));
        Mockito.when(productService.update(product)).thenReturn(updatedProduct);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProductRequestDto(updatedProduct.getName(), updatedProduct.getProductPrice()))
                .when()
                .put("/products/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("wheel"))
                .body("productPrice", Matchers.equalTo(500));
    }
}
