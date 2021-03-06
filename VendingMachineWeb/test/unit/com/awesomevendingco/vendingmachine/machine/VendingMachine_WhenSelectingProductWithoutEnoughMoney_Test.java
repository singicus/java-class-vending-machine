package com.awesomevendingco.vendingmachine.machine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.awesomevendingco.vendingmachine.coins.MockCoinTranslator;
import com.awesomevendingco.vendingmachine.products.MockProductValuator;

public class VendingMachine_WhenSelectingProductWithoutEnoughMoney_Test {

    private VendingMachine machine;

    @Before
    public void setup() {
        machine = new VendingMachine();
        machine.setCoinTranslator(new MockCoinTranslator());
        machine.setProductValuator(new MockProductValuator());
        machine.selectProduct(MockProductValuator.GOOD_PRODUCT);
    }

    @Test
    public void it_doesn_not_put_product_in_product_chute() {
        assertFalse(machine.getProductChute().contains(MockProductValuator.GOOD_PRODUCT));
    }

    @Test
    public void it_displays_message_stating_price_of_product() {
        assertEquals("PRICE 0.45", machine.getMessage());
    }

}
