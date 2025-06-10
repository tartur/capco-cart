package com.tartur.capco.domain.model.cart;

import com.tartur.capco.domain.model.product.Product;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toUnmodifiableMap;

public class Cart {
    private final Map<Product, AtomicInteger> products = new ConcurrentHashMap<>();

    public boolean isEmpty() {
        return getProducts().isEmpty();
    }

    public Map<Product, Integer> getProducts() {
        return products.entrySet().stream()
                .filter(e -> e.getValue().intValue() > 0)
                .collect(toUnmodifiableMap(Map.Entry::getKey, e -> e.getValue().get()));
    }

    public void add(int deltaQty, Product product) {
        if (deltaQty != 0) {
            products.compute(product, (p, q) -> computeNewQuantity(q, deltaQty));
        }
    }

    private AtomicInteger computeNewQuantity(AtomicInteger current, int delta) {
        if (current == null) {
            current = new AtomicInteger(Math.max(0, delta));
        } else {
            current.getAndUpdate(old -> Math.max(0, old + delta));
        }
        return current;
    }
}
