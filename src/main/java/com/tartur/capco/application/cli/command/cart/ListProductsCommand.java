package com.tartur.capco.application.cli.command.cart;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.product.Product;
import com.tartur.capco.domain.service.product.ProductRepository;

import java.util.Comparator;

public class ListProductsCommand extends CLICommand<Boolean> {

    private final ProductRepository repository;

    protected ListProductsCommand(ProductRepository repository) {
        super("Afficher la liste des produits", "l");
        this.repository = repository;
    }

    @Override
    public Boolean execute() {
        repository.findAll()
                .stream().sorted(Comparator.comparing(Product::id))
                .forEach(p -> print("%s. %s\t\t--\t%s\n", p.id(), p.model(), p));
        return true;
    }
}
