package com.tartur.capco.domain.service.pricing;

import com.tartur.capco.domain.model.pricing.PricingRule;

import java.util.List;

public interface PricingRuleRepository {
    void add(PricingRule rule);

    List<PricingRule> findAll();
}
