package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.order;

import java.util.List;

public interface orderRepository {
    List<order> findAll(long phone);

    int addOrder(order order);

    int deleteById(long customerId);
}
