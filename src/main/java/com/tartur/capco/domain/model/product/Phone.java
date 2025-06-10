package com.tartur.capco.domain.model.product;

public record Phone(int id, String model, ProductCategory category) implements Product {
}
