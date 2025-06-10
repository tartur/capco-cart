package com.tartur.capco.application.cli.command.client;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.model.client.IndividualClient;

public class ChooseIndividualCommand extends CLICommand<Client> {

    protected ChooseIndividualCommand() {
        super("Particulier", "1");
    }

    @Override
    public IndividualClient execute() {
        String firstName = request("Prénom: ");
        String lastName = request("Nom: ");
        return new IndividualClient(0, firstName, lastName);
    }
}
