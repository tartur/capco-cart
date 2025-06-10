package com.tartur.capco.application.cli.command.cart;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.service.pricing.PricingService;

import java.math.BigDecimal;

public class OverviewCommand extends CLICommand<Boolean> {
    private final PricingService service;
    private final Cart cart;
    private final Client client;

    protected OverviewCommand(PricingService service, Cart cart, Client client) {
        super("Afficher le panier", "v");
        this.service = service;
        this.cart = cart;
        this.client = client;
    }

    @Override
    public Boolean execute() {
        if (cart.isEmpty()) {
            print("\nVotre panier est vide!\n");
        } else {
            print("\nLa liste des produits de votre panier est:\n");
            cart.getProducts().forEach((product, qty) -> print("- %s (%s)\n", product.model(), qty));
            BigDecimal cost = service.calculateCost(cart, client);
            print("\nLe coût total de votre panier est: %s€\n", cost);
        }
        return true;
    }
}
