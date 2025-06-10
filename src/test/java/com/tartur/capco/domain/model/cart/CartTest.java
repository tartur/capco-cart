package com.tartur.capco.domain.model.cart;

import com.tartur.capco.domain.model.product.ProductCategory;
import com.tartur.capco.domain.model.product.Phone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CartTest {

    public static final String MODELE_PRODUIT = "APPLE IPHONE XR";

    @Test
    void when_no_product_is_added_cart_is_empty() {
        var panier = new Cart();
        assertThat(panier.getProducts()).isEmpty();
    }

    @Test
    void when_a_product_is_added_its_quantity_increases() {
        var produit = new Phone(1, MODELE_PRODUIT, ProductCategory.PREMIUM);
        var panier = new Cart();

        panier.add(3, produit);
        panier.add(2, produit);

        assertThat(panier.getProducts()).containsKey(produit);
        assertThat(panier.getProducts().get(produit)).isEqualTo(5);
    }

    @Test
    void when_a_product_is_removed_its_quantity_decreases() {
        var produit = new Phone(1, MODELE_PRODUIT, ProductCategory.PREMIUM);
        var panier = new Cart();

        panier.add(3, produit);
        panier.add(-2, produit);

        assertThat(panier.getProducts()).containsKey(produit);
        assertThat(panier.getProducts().get(produit)).isEqualTo(1);
    }

    @Test
    void when_a_product_quantity_is_zero_then_product_is_removed_from_the_cart() {
        var produit = new Phone(1, MODELE_PRODUIT, ProductCategory.PREMIUM);
        var panier = new Cart();

        panier.add(3, produit);
        panier.add(-3, produit);

        assertThat(panier.getProducts()).doesNotContainKey(produit);
    }
}