package com.tartur.capco.infrastructure.persistence.inmemory;

import com.tartur.capco.domain.model.pricing.PricingRule;
import com.tartur.capco.domain.service.pricing.PricingRuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class InMemoryPricingRuleRepository implements PricingRuleRepository {
    private final Collection<PricingRule> rules = new HashSet<>();

    @Override
    public void add(PricingRule rule) {
        rules.add(rule);
    }

    @Override
    public List<PricingRule> findAll() {
        return new ArrayList<>(rules);
    }
}
