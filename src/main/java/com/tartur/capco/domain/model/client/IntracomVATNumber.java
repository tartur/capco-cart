package com.tartur.capco.domain.model.client;

import java.util.regex.Pattern;

public record IntracomVATNumber(String value) {
    public IntracomVATNumber {
        if (null == value || !Pattern.matches("FR\\d{11}", value))
            throw new IllegalArgumentException("Le numéro TVA intracom '" + value + "' est invalide!");
    }
}
