package com.tartur.capco.domain.model.pricing;

import com.tartur.capco.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class PricingRule {
    private final ClientPricingCategory expectedClientCategory;
    private final BigDecimal price;

    protected PricingRule(ClientPricingCategory expectedClientCategory, BigDecimal price) {
        this.expectedClientCategory = expectedClientCategory;
        this.price = price;
    }

    public boolean match(ClientPricingCategory clientCategory, Product product) {
        return expectedClientCategory == clientCategory;
    }

    public BigDecimal price() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expectedClientCategory);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof PricingRule rule && rule.expectedClientCategory == expectedClientCategory);
    }
}
