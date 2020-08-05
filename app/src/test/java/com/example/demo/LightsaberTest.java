package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightsaberTest {

    @Test
    public void InstantiatesWithSerialNumber() {
        LightSaber saber = new LightSaber(5L);

        assertEquals(5L, saber.getJediSerialNumber());
    }

    @Test
    public void InstantiatesWith300MinutesOfCharge() {
        LightSaber saber = new LightSaber(5L);

        assertEquals(100.0f, saber.getCharge());
        assertEquals(600.0f, saber.getRemainingMinutes());
    }

    @Test
    public void InstantiatesWithGreenColor() {
        LightSaber saber = new LightSaber(5L);

        assertEquals("green", saber.getColor());
    }

    @Test
    public void CanSetColor() {
        LightSaber saber = new LightSaber(5L);

        saber.setColor("purple");

        assertEquals("purple", saber.getColor());
    }

    @Test
    public void CanSetCharge() {
        LightSaber saber = new LightSaber(5L);

        saber.setCharge(50.0f);

        assertEquals(50.0f, saber.getCharge());
    }

    @Test
    public void CanUseCharge() {
        LightSaber saber = new LightSaber(5L);

        saber.use(60.0f);

        assertEquals(540.0f, saber.getRemainingMinutes());
    }

    @Test
    public void CanRecharge() {
        LightSaber saber = new LightSaber(5L);

        saber.use(10.0f);
        saber.recharge();

        assertEquals(100.0f, saber.getCharge());
    }
}
