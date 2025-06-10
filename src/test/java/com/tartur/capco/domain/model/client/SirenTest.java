package com.tartur.capco.domain.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SirenTest {


    @Test
    void create_instance_when_valid_value() {
        assertDoesNotThrow(() -> new Siren("123456789"));
    }

    @Test
    void throws_IAE_when_invalid_value() {
        assertThrows(IllegalArgumentException.class, () -> new Siren("0123456789"));
    }
}