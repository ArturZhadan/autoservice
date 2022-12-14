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
    private static final double PROPOSALS_DISCOUNT = 0.02;
    private static final double PRODUCTS_DISCOUNT = 0.01;
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
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find order by id " + id));
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return orderRepository.findAllById(ids);
    }

    @Override
    public List<Proposal> findAllProposalsByOrderId(Long id) {
        return orderRepository.findAllProposalsByOrderId(id);
    }

    @Override
    public List<Product> findAllProductsByOrderId(Long id) {
        return orderRepository.findAllProductsByOrderId(id);
    }

    @Override
    public void updateOrderStatus(Long id, String orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.valueOf(orderStatus));
            if (orderStatus.equalsIgnoreCase("completed_successfully")
                    || orderStatus.equalsIgnoreCase("not_completed_successfully")) {
                order.setCompletionDate(Date.from(LocalDate.now()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            orderRepository.save(order);
        }
    }

    @Override
    public void updateProductsList(Long id, List<Long> productsIds) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            List<Product> products = order.getProducts();
            products.addAll(productService.findAllById(productsIds));
            order.setProducts(products);
            orderRepository.save(order);
        }
    }

    @Override
    public BigDecimal getPrice(Long id) {
        Order order = findById(id);
        List<Proposal> proposals = findAllProposalsByOrderId(id);
        double proposalsPrice = proposals.stream()
                .map(Proposal::getProposalPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        proposalsPrice = proposalsPrice - (proposals.size() * PROPOSALS_DISCOUNT);
        List<Product> products = findAllProductsByOrderId(id);
        double productsPrice = products.stream()
                .map(Product::getProductPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        productsPrice = productsPrice - (products.size() * PRODUCTS_DISCOUNT);
        order.setOrderPrice(BigDecimal.valueOf(proposalsPrice)
                .add(BigDecimal.valueOf(productsPrice)));
        orderRepository.save(order);
        return order.getOrderPrice();
    }

}
