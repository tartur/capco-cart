package com.tartur.capco.domain.exception;

import com.tartur.capco.domain.model.client.Client;

public class UnknownClientPricing extends RuntimeException {
    public UnknownClientPricing(Client client) {
        super("Unknown client pricing: " + client);
    }
}
