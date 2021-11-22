package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.product;

public interface productRepository {
    int add(product product);

    product findById(int id);
}
