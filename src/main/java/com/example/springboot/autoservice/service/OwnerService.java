package com.example.springboot.autoservice.service;

import com.example.springboot.autoservice.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner update(Owner owner);

    Owner findById(Long id);
}
