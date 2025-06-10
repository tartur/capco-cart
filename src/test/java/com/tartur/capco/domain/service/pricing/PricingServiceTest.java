package com.tartur.capco.domain.service.pricing;

import com.tartur.capco.domain.exception.UnknownClientPricing;
import com.tartur.capco.domain.exception.UnknownProductPricing;
import com.tartur.capco.domain.model.cart.Cart;
import com.tartur.capco.domain.model.client.Client;
import com.tartur.capco.domain.model.client.CompanyClient;
import com.tartur.capco.domain.model.client.IndividualClient;
import com.tartur.capco.domain.model.client.Siren;
import com.tartur.capco.domain.model.pricing.PricingLaptopRule;
import com.tartur.capco.domain.model.pricing.PricingPhoneRule;
import com.tartur.capco.domain.model.product.Laptop;
import com.tartur.capco.domain.model.product.Phone;
import com.tartur.capco.domain.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.tartur.capco.domain.model.pricing.ClientPricingCategory.*;
import static com.tartur.capco.domain.model.product.ProductCategory.MID_RANGE;
import static com.tartur.capco.domain.model.product.ProductCategory.PREMIUM;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricingServiceTest {
    public static final Client CLIENT_LAMBDA = mock(Client.class);
    public static final Product PRODUIT_LAMBDA = mock(Product.class);
    public static final Client DUCOBU = new IndividualClient(1, "encorvou", "ducobu");
    public static final Client PETIT_PRO = new CompanyClient(2,
            new Siren("612039073"), "smally", 9_000_000);
    public static final Client GROS_PRO = new CompanyClient(3,
            new Siren("612039074"), "bigo", 11_000_000);
    public static final Phone PHONE_PREMIUM = new Phone(1, "IPHONE 15", PREMIUM);
    public static final Phone PHONE_MOYEN = new Phone(2, "OPPO 12", MID_RANGE);
    public static final Laptop LAPTOP = new Laptop(3, "Surface 5");
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        var pricingRepository = mock(PricingRuleRepository.class);
        when(pricingRepository.findAll()).thenReturn(asList(
                new PricingPhoneRule(INDIVIDUAL, PREMIUM, new BigDecimal("1500")),
                new PricingPhoneRule(INDIVIDUAL, MID_RANGE, new BigDecimal("800")),
                new PricingLaptopRule(INDIVIDUAL, new BigDecimal("1200")),
                new PricingPhoneRule(SMALL_COMPANY, PREMIUM, new BigDecimal("1150")),
                new PricingPhoneRule(SMALL_COMPANY, MID_RANGE, new BigDecimal("600")),
                new PricingLaptopRule(SMALL_COMPANY, new BigDecimal("1000")),
                new PricingPhoneRule(BIG_COMPANY, PREMIUM, new BigDecimal("1000")),
                new PricingPhoneRule(BIG_COMPANY, MID_RANGE, new BigDecimal("550")),
                new PricingLaptopRule(BIG_COMPANY, new BigDecimal("900"))
        ));
        pricingService = new PricingService(10_000_000, pricingRepository);
    }

    @Test
    void when_cart_is_empty_then_cost_is_zero() {
        var panier = new Cart();

        var montant = pricingService.calculateCost(panier, DUCOBU);

        assertThat(montant).isZero();
    }

    @Test
    void when_cart_contains_unknown_product_then_throw_exception() {
        var panier = new Cart();
        panier.add(1, PRODUIT_LAMBDA);

        assertThatThrownBy(() -> pricingService.calculateCost(panier, DUCOBU)).isInstanceOf(UnknownProductPricing.class);
    }


    @Test
    void when_cart_associated_with_not_supported_client_then_throw_exception() {
        var panier = new Cart();
        panier.add(1, PHONE_PREMIUM);

        assertThatThrownBy(() -> pricingService.calculateCost(panier, CLIENT_LAMBDA)).isInstanceOf(UnknownClientPricing.class);
    }

    @Test
    void when_individual_client_cart_contains_a_premium_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_PREMIUM);

        assertThat(pricingService.calculateCost(panier, DUCOBU)).isEqualByComparingTo("1500");
    }

    @Test
    void when_individual_client_cart_contains_a_mid_range_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_MOYEN);

        assertThat(pricingService.calculateCost(panier, DUCOBU)).isEqualByComparingTo("800");
    }

    @Test
    void when_individual_client_cart_contains_a_laptop() {
        var panier = new Cart();
        panier.add(1, LAPTOP);

        assertThat(pricingService.calculateCost(panier, DUCOBU)).isEqualByComparingTo("1200");
    }

    @Test
    void when_big_company_client_cart_contains_a_premium_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_PREMIUM);

        assertThat(pricingService.calculateCost(panier, GROS_PRO)).isEqualByComparingTo("1000");
    }

    @Test
    void when_big_company_client_cart_contains_a_mid_range_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_MOYEN);

        assertThat(pricingService.calculateCost(panier, GROS_PRO)).isEqualByComparingTo("550");
    }

    @Test
    void when_big_company_client_cart_contains_a_laptop() {
        var panier = new Cart();
        panier.add(1, LAPTOP);

        assertThat(pricingService.calculateCost(panier, GROS_PRO)).isEqualByComparingTo("900");
    }

    @Test
    void when_small_company_client_cart_contains_a_premium_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_PREMIUM);

        assertThat(pricingService.calculateCost(panier, PETIT_PRO)).isEqualByComparingTo("1150");
    }

    @Test
    void when_small_company_client_cart_contains_a_mid_range_phone() {
        var panier = new Cart();
        panier.add(1, PHONE_MOYEN);

        assertThat(pricingService.calculateCost(panier, PETIT_PRO)).isEqualByComparingTo("600");
    }

    @Test
    void when_small_company_client_cart_contains_a_laptop() {
        var panier = new Cart();
        panier.add(1, LAPTOP);

        assertThat(pricingService.calculateCost(panier, PETIT_PRO)).isEqualByComparingTo("1000");
    }

    @Test
    void when_cart_contains_multiple_products_with_different_quantities_compute_the_sum() {
        var panier = new Cart();
        panier.add(1, LAPTOP);
        panier.add(2, LAPTOP);
        panier.add(2, PHONE_PREMIUM);
        panier.add(3, PHONE_MOYEN);

        assertThat(pricingService.calculateCost(panier, PETIT_PRO)).isEqualByComparingTo("7100");
    }
}