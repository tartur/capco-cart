package com.tartur.capco.application;

import com.tartur.capco.application.cli.CLIShoppingCartSession;
import com.tartur.capco.domain.model.pricing.PricingLaptopRule;
import com.tartur.capco.domain.model.pricing.PricingPhoneRule;
import com.tartur.capco.domain.model.product.Laptop;
import com.tartur.capco.domain.model.product.Phone;
import com.tartur.capco.domain.model.product.ProductCategory;
import com.tartur.capco.domain.service.pricing.PricingRuleRepository;
import com.tartur.capco.domain.service.product.ProductRepository;
import com.tartur.capco.infrastructure.persistence.inmemory.InMemoryPricingRuleRepository;
import com.tartur.capco.infrastructure.persistence.inmemory.InMemoryProductRepository;

import java.math.BigDecimal;

import static com.tartur.capco.domain.model.pricing.ClientPricingCategory.*;
import static com.tartur.capco.domain.model.product.ProductCategory.MID_RANGE;
import static com.tartur.capco.domain.model.product.ProductCategory.PREMIUM;

public class CLIApplication {
    public static final int REVENUE_THRESHOLD = 10_000_000;

    public static void main(String[] args) {
        var productRepository = initializeProductCatalog();
        var pricingRepository = initializePricing();
        var session = new CLIShoppingCartSession(productRepository, pricingRepository, REVENUE_THRESHOLD);
        session.start();
        while (session.shouldRun()) {
            session.run();
        }
    }

    private static ProductRepository initializeProductCatalog() {
        var id = 0;
        var repository = new InMemoryProductRepository();
        repository.add(new Phone(++id, "IPhone 15", ProductCategory.PREMIUM));
        repository.add(new Phone(++id, "Samsung S25", ProductCategory.PREMIUM));
        repository.add(new Phone(++id, "IPhone SE", ProductCategory.MID_RANGE));
        repository.add(new Phone(++id, "XIAOMI X13", ProductCategory.MID_RANGE));
        repository.add(new Laptop(++id, "Macbook air"));
        repository.add(new Laptop(++id, "MS surface"));
        return repository;
    }

    private static PricingRuleRepository initializePricing() {
        var repository = new InMemoryPricingRuleRepository();
        repository.add(new PricingPhoneRule(INDIVIDUAL, PREMIUM, new BigDecimal("1500")));
        repository.add(new PricingPhoneRule(INDIVIDUAL, MID_RANGE, new BigDecimal("800")));
        repository.add(new PricingLaptopRule(INDIVIDUAL, new BigDecimal("1200")));
        repository.add(new PricingPhoneRule(SMALL_COMPANY, PREMIUM, new BigDecimal("1150")));
        repository.add(new PricingPhoneRule(SMALL_COMPANY, MID_RANGE, new BigDecimal("600")));
        repository.add(new PricingLaptopRule(SMALL_COMPANY, new BigDecimal("1000")));
        repository.add(new PricingPhoneRule(BIG_COMPANY, PREMIUM, new BigDecimal("1000")));
        repository.add(new PricingPhoneRule(BIG_COMPANY, MID_RANGE, new BigDecimal("550")));
        repository.add(new PricingLaptopRule(BIG_COMPANY, new BigDecimal("900")));
        return repository;
    }
}