package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.OrderStatus;
import com.example.springboot.autoservice.model.Product;
import com.example.springboot.autoservice.model.Proposal;
import com.example.springboot.autoservice.repository.OrderRepository;
import com.example.springboot.autoservice.service.OrderService;
import com.example.springboot.autoservice.service.ProductService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return orderRepository.findAllById(ids);
    }

    @Override
    public void updateOrderStatus(Long id, String key, String value) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (key.equalsIgnoreCase("orderStatus")) {
                order.setOrderStatus(OrderStatus.valueOf(value));
            }
            if (value.equalsIgnoreCase("completed_successfully")
                    || value.equalsIgnoreCase("not_completed_successfully")) {
                order.setCompletionDate(Date.from(LocalDate.now()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            orderRepository.save(order);
        }
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find order by id " + id));
    }

    @Override
    public void updateProductsList(Long id, String key, List<Long> productsIds) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            List<Product> products = order.getProducts();
            if (key.equalsIgnoreCase("productsIds")) {
                for (Long productId: productsIds) {
                    products.add(productService.findById(productId));
                }
                order.setProducts(products);
            }
            orderRepository.save(order);
        }
    }

    @Override
    public BigDecimal getPrice(Long id) {
        Order order = findById(id);
        List<Proposal> proposals = findById(id).getProposals();
        double proposalsPrice = proposals.stream()
                .map(Proposal::getProposalPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        proposalsPrice = proposalsPrice - (proposals.size() * 0.02);
        List<Product> products = findById(id).getProducts();
        double productsPrice = products.stream()
                .map(Product::getProductPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        productsPrice = productsPrice - (products.size() * 0.01);
        order.setOrderPrice(BigDecimal.valueOf(proposalsPrice)
                .add(BigDecimal.valueOf(productsPrice)));
        orderRepository.save(order);
        return order.getOrderPrice();
    }
}
