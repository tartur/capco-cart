package com.tartur.capco.application.cli;

import com.tartur.capco.application.cli.command.client.ClientHomeCommand;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.service.pricing.PricingRuleRepository;
import com.tartur.capco.domain.service.pricing.PricingService;
import com.tartur.capco.domain.service.product.ProductRepository;

public class CLIShoppingCartSession {
    private final ProductRepository repository;
    private final PricingService pricingService;
    private CLICartController cartController;

    public CLIShoppingCartSession(ProductRepository productRepository,
                                  PricingRuleRepository pricingRepository,
                                  double revenueThreshold) {
        this.repository = productRepository;
        this.pricingService = new PricingService(revenueThreshold, pricingRepository);
    }

    public void start() {
        Client client = new ClientHomeCommand().execute();
        cartController = new CLICartController(pricingService, repository, client, new Cart());
    }

    public boolean shouldRun() {
        return cartController != null;
    }

    public void run() {
        if (cartController == null)
            throw new NoActiveSession();

        if (!cartController.shop())
            cartController = null;
    }
}
