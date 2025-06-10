package com.tartur.capco.infrastructure.persistence.inmemory;

import com.tartur.capco.domain.model.product.Product;
import com.tartur.capco.domain.service.product.ProductRepository;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Collection<Product> products = new HashSet<>();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Product> findById(int id) {
        return products.stream().filter(product -> product.id() == id).findFirst();
    }
}
