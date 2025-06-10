package com.tartur.capco.domain.service.product;

import com.tartur.capco.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void add(Product product);

    List<Product> findAll();

    Optional<Product> findById(int id);
}
