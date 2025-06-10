package com.tartur.capco.application.cli.command.cart;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.service.product.ProductRepository;

public class RemoveProductCommand extends CLICommand<Boolean> {

    private final ProductRepository repository;
    private final Cart cart;

    protected RemoveProductCommand(ProductRepository repository, Cart cart) {
        super("Enlever un produit du panier", "x");
        this.repository = repository;
        this.cart = cart;
    }

    @Override
    public Boolean execute() {
        int id = repeatTillValid(() -> Integer.parseInt(request("Id du produit à enlever: ")));
        int qty = repeatTillValid(() -> Integer.parseInt(request("La quantité à enlever: ")));
        repository.findById(id).ifPresent(p -> cart.add(-qty, p));
        return true;
    }
}
