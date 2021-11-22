package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.transaction;

import java.util.List;

public interface transactionRepository {
    List<transaction> findAll(long phone);

    int addOrder(transaction transaction);

    int deleteById(long phone);
}
