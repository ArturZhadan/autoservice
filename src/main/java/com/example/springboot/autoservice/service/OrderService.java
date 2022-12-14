package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    List<Order> findAllById(List<Long> ids);

    void updateOrderStatus(Long id, String orderStatus);

    Order findById(Long id);

    void updateProductsList(Long id, List<Long> productsIds);

    BigDecimal getPrice(Long id);

    List<Proposal> findAllProposalsByOrderId(Long id);

    List<Product> findAllProductsByOrderId(Long id);
}
