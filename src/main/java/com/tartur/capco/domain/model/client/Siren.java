package com.tartur.capco.domain.model.client;

import java.util.regex.Pattern;

public record Siren(String value) {
    public Siren {
        if (null == value || !Pattern.matches("\\d{9}", value))
            throw new IllegalArgumentException("Le numéro SIREN '" + value + "' est invalide!");
    }
}
