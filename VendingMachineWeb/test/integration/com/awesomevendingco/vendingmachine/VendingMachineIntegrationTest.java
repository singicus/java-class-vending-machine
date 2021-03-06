package com.awesomevendingco.vendingmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awesomevendingco.vendingmachine.machine.VendingMachine;

public class VendingMachineIntegrationTest {

    private VendingMachine machine;

    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        machine = (VendingMachine) context.getBean("machine");
    }

    @Test
    public void it_takes_nickels() {
        machine.insertCoin("NICKEL");
        assertEquals("0.05", machine.getBalance());
    }

    @Test
    public void it_takes_dimes() {
        machine.insertCoin("DIME");
        assertEquals("0.10", machine.getBalance());
    }

    @Test
    public void it_takes_quarters() {
        machine.insertCoin("QUARTER");
        assertEquals("0.25", machine.getBalance());
    }

    @Test
    public void it_does_not_take_pennies() {
        machine.insertCoin("PENNY");
        assertEquals("0.00", machine.getBalance());
        assertTrue(machine.getCoinReturn().contains("PENNY"));
    }

    @Test
    public void it_sells_cola() {
        insertDollar();
        machine.selectProduct("COLA");
        assertTrue(machine.getProductChute().contains("COLA"));
    }

    public void it_sells_chips() {
        insertDollar();
        machine.selectProduct("CHIPS");
        assertTrue(machine.getProductChute().contains("CHIPS"));
    }

    public void it_sells_candy() {
        insertDollar();
        machine.selectProduct("CANDY");
        assertTrue(machine.getProductChute().contains("CANDY"));
    }

    public void it_does_not_sell_coffee() {
        machine.selectProduct("COFFEE");
        assertEquals("INVALID PRODUCT", machine.getMessage());
        assertTrue(machine.getProductChute().isEmpty());
    }

    @Test
    public void it_sells_cola_for_a_dollar() {
        machine.selectProduct("COLA");
        assertEquals("PRICE 1.00", machine.getMessage());
    }

    public void it_sells_chips_for_50_cents() {
        machine.selectProduct("CHIPS");
        assertEquals("PRICE 0.50", machine.getMessage());
    }

    public void it_sells_candy_for_65_cents() {
        machine.selectProduct("CANDY");
        assertEquals("PRICE 0.65", machine.getMessage());
    }

    private void insertDollar() {
        machine.insertCoin("QUARTER");
        machine.insertCoin("QUARTER");
        machine.insertCoin("QUARTER");
        machine.insertCoin("QUARTER");
    }
}
