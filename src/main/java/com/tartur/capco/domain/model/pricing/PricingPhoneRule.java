package com.tartur.capco.domain.model.pricing;

import com.tartur.capco.domain.model.product.Phone;
import com.tartur.capco.domain.model.product.Product;
import com.tartur.capco.domain.model.product.ProductCategory;

import java.math.BigDecimal;

public class PricingPhoneRule extends PricingRule {
    private final ProductCategory expectedProductCategory;

    public PricingPhoneRule(ClientPricingCategory expectedClientCategory,
                            ProductCategory expectedProductCategory,
                            BigDecimal price) {
        super(expectedClientCategory, price);
        this.expectedProductCategory = expectedProductCategory;
    }

    @Override
    public boolean match(ClientPricingCategory clientCategory, Product product) {
        if (product instanceof Phone phone) {
            return expectedProductCategory == phone.category() && super.match(clientCategory, product);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && (obj instanceof PricingPhoneRule other && other.expectedProductCategory == expectedProductCategory);
    }
}
