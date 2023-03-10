package com.example.product.service;

import java.util.List;

public interface ICoreService <T>{
    List<T> findAll();

    T findById(Long id);
    void save(T t);

    void remove(Long id);
}
