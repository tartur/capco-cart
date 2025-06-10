package com.tartur.capco.application.cli;

import com.tartur.capco.application.cli.command.cart.CartHomeCommand;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.service.pricing.PricingService;
import com.tartur.capco.domain.service.product.ProductRepository;

class CLICartController {
    private final PricingService pricingService;
    private final ProductRepository repository;
    private final Client client;
    private final Cart cart;

    CLICartController(PricingService pricingService, ProductRepository repository, Client client, Cart cart) {
        this.pricingService = pricingService;
        this.repository = repository;
        this.client = client;
        this.cart = cart;
    }

    boolean shop() {
        CartHomeCommand homeCommand = new CartHomeCommand(repository, pricingService, cart, client);
        return homeCommand.execute();
    }
}
