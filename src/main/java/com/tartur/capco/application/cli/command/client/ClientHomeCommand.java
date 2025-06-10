package com.tartur.capco.application.cli.command.client;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.client.Client;

public class ClientHomeCommand extends CLICommand<Client> {

    public ClientHomeCommand() {
        super("", "", new ChooseIndividualCommand(), new ChooseCompanyCommand());
    }

    @Override
    public Client execute() {
        print("Veuillez choisir le type de client:\n");
        showChildren();
        return repeatTillValid(() -> chooseChild().map(CLICommand::execute).orElseThrow(IllegalArgumentException::new));
    }
}
