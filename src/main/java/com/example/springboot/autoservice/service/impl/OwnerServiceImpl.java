package com.example.springboot.autoservice.service.impl;

import com.example.springboot.autoservice.model.Order;
import com.example.springboot.autoservice.model.Owner;
import com.example.springboot.autoservice.repository.OwnerRepository;
import com.example.springboot.autoservice.service.OwnerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public List<Order> findAllOrdersByOwnerId(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find owner by id " + id)).getOrders();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can`t find owner by id " + id));
    }
}
