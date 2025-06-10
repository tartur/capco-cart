package com.tartur.capco.application.cli.command.cart;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.service.pricing.PricingService;
import com.tartur.capco.domain.service.product.ProductRepository;

public class CartHomeCommand extends CLICommand<Boolean> {

    public CartHomeCommand(ProductRepository repository, PricingService service, Cart cart, Client client) {
        super("", "",
                new ListProductsCommand(repository),
                new AddProductCommand(repository, cart),
                new RemoveProductCommand(repository, cart),
                new OverviewCommand(service, cart, client),
                new QuitShoppingCommand());
    }

    @Override
    public Boolean execute() {
        print("\nQue souhaitez-vous faire?\n");
        showChildren();
        return repeatTillValid(() -> chooseChild().map(CLICommand::execute).orElseThrow(IllegalArgumentException::new));
    }
}
