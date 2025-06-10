package com.tartur.capco.domain.exception;

import com.tartur.capco.domain.model.product.Product;

public class UnknownProductPricing extends RuntimeException {
    public UnknownProductPricing(Product product) {
        super("Unknown product pricing for: " + product);
    }
}
