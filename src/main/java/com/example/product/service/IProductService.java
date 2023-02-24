package com.example.product.service;

import com.example.product.model.Product;

import java.util.List;

public interface IProductService extends ICoreService<Product> {
    List<Product> searchByName(String name);
}
