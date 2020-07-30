package com.demo.watek;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SimpleClassTest {

    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        assertEquals(1, 1);
    }
}