package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.customer;

import java.util.List;

public interface customerRepository {
    List<customer> findAll();

    int add(customer customer);

    customer findById(long phone);

    int update(customer customer);

    int deleteById( long phone);
}
