package com.tartur.capco.application.cli.command.client;

import com.tartur.capco.application.cli.command.CLICommand;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.model.client.CompanyClient;
import com.tartur.capco.domain.model.client.IntracomVATNumber;
import com.tartur.capco.domain.model.client.Siren;

public class ChooseCompanyCommand extends CLICommand<Client> {

    protected ChooseCompanyCommand() {
        super("Professionnel", "2");
    }

    @Override
    public CompanyClient execute() {
        String sirenValue = request("Numéro SIREN (9 chiffres): ");
        String legalName = request("Raison sociale: ");
        double revenue = repeatTillValid(() -> Double.parseDouble(request("Chiffre d'affaires: ")));
        String vatNumber = request("Numéro TVA intracommunautaire (format FR suivi de 11 chiffres, ou laissez vide): ");

        if (vatNumber.isEmpty()) {
            return new CompanyClient(1, new Siren(sirenValue), legalName, revenue);
        } else {
            return new CompanyClient(1, new Siren(sirenValue), legalName, revenue, new IntracomVATNumber(vatNumber));
        }
    }
}
