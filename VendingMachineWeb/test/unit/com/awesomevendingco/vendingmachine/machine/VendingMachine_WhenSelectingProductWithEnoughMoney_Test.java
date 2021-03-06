package com.awesomevendingco.vendingmachine.machine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.awesomevendingco.vendingmachine.coins.MockCoinTranslator;
import com.awesomevendingco.vendingmachine.products.MockProductValuator;

public class VendingMachine_WhenSelectingProductWithEnoughMoney_Test {

    private VendingMachine machine;

    @Before
    public void setup() {
        machine = new VendingMachine();
        machine.setCoinTranslator(new MockCoinTranslator());
        machine.setProductValuator(new MockProductValuator());
        machine.insertCoin(MockCoinTranslator.GOOD_COIN);
        machine.selectProduct(MockProductValuator.GOOD_PRODUCT);
    }

    @Test
    public void it_puts_product_in_product_chute() {
        assertTrue(machine.getProductChute().contains(MockProductValuator.GOOD_PRODUCT));
    }

    @Test
    public void it_displays_message_of_thank_you() {
        assertEquals("THANK YOU", machine.getMessage());
    }

    @Test
    public void it_displays_balance_of_zero() {
        assertEquals("0.00", machine.getBalance());
    }

}
