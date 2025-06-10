package com.tartur.capco.domain.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntracomVATNumberTest {


    @Test
    void create_instance_when_valid_value() {
        assertDoesNotThrow(() -> new IntracomVATNumber("FR00123456789"));
    }

    @Test
    void throws_IAE_when_invalid_value() {
        assertThrows(IllegalArgumentException.class, () -> new IntracomVATNumber("FR0123456789"));
    }
}