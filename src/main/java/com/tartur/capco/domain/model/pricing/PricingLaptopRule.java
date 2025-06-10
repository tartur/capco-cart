package com.tartur.capco.domain.model.pricing;

import com.tartur.capco.domain.model.product.Laptop;
import com.tartur.capco.domain.model.product.Product;

import java.math.BigDecimal;

public class PricingLaptopRule extends PricingRule {
    public PricingLaptopRule(ClientPricingCategory expectedClientCategory, BigDecimal price) {
        super(expectedClientCategory, price);
    }

    @Override
    public boolean match(ClientPricingCategory clientCategory, Product product) {
        return product instanceof Laptop && super.match(clientCategory, product);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof PricingLaptopRule;
    }
}
