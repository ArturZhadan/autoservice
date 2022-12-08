package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import java.util.List;

public interface OwnerService {
    Owner save(Owner owner);

    Owner update(Owner owner);

    List<Order> findAllOrdersByOwnerId(Long id);

    Owner findById(Long id);
}
