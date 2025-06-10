package com.tartur.capco.application.cli.command.cart;

import com.tartur.capco.application.cli.command.CLICommand;

public class QuitShoppingCommand extends CLICommand<Boolean> {


    protected QuitShoppingCommand() {
        super("Quitter le panier", "q");
    }

    @Override
    public Boolean execute() {
        return false;
    }
}
