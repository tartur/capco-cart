package com.tartur.capco.domain.service.pricing;

import com.tartur.capco.domain.exception.UnknownClientPricing;
import com.tartur.capco.domain.exception.UnknownProductPricing;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.model.client.CompanyClient;
import com.tartur.capco.domain.model.client.IndividualClient;
import com.tartur.capco.domain.model.pricing.ClientPricingCategory;
import com.tartur.capco.domain.model.pricing.PricingRule;
import com.tartur.capco.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

public class PricingService {
    private final double revenueThreshold;
    private final PricingRuleRepository repository;

    public PricingService(double revenueThreshold, PricingRuleRepository repository) {
        this.revenueThreshold = revenueThreshold;
        this.repository = repository;
    }

    public BigDecimal calculateCost(Cart cart, Client client) {
        ClientPricingCategory clientCategory = resolveClientCategory(client);
        List<PricingRule> rules = repository.findAll();
        return cart.getProducts().entrySet().stream()
                .reduce(ZERO,
                        (total, entry) -> findRule(rules, clientCategory, entry.getKey())
                                .map(rule -> rule.price().multiply(BigDecimal.valueOf(entry.getValue())))
                                .map(total::add)
                                .orElseThrow(() -> new UnknownProductPricing(entry.getKey())),
                        BigDecimal::add);
    }

    private ClientPricingCategory resolveClientCategory(Client client) {
        return switch (client) {
            case IndividualClient ignored -> ClientPricingCategory.INDIVIDUAL;
            case CompanyClient pro when pro.getRevenue() >= revenueThreshold -> ClientPricingCategory.BIG_COMPANY;
            case CompanyClient pro when pro.getRevenue() < revenueThreshold -> ClientPricingCategory.SMALL_COMPANY;
            default -> throw new UnknownClientPricing(client);
        };
    }

    private Optional<PricingRule> findRule(List<PricingRule> rules, ClientPricingCategory category, Product product) {
        return rules.stream().filter(r -> r.match(category, product)).findFirst();
    }
}
