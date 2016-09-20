package com.swissre.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fredrikason on 20/09/2016.
 */
public class StringCalculatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddNumbers() {
        assertEquals(1+2, StringCalculator.add("1,2"));
        assertEquals(1+2+3, StringCalculator.add("1,2,3"));
        assertEquals(1+23, StringCalculator.add("1,23"));
    }

    @Test
    public void testAddEmtpyNumbers() {
        assertEquals(0, StringCalculator.add(""));
        assertEquals(0, StringCalculator.add(null));
    }

    @Test
    public void testAddDifferentDelimiters() {
        assertEquals(1+2+3, StringCalculator.add("1,2,3"));
        assertEquals(1+2+3, StringCalculator.add("1 2 3"));
        assertEquals(1+2+3, StringCalculator.add("1/2/3"));
    }

    @Test(expected = RuntimeException.class )
    public void testAddNegativeNumbers() {
        StringCalculator.add("1,-2");
    }

    @Test
    public void testAddLargeNumbers() {
        assertEquals(1+100, StringCalculator.add("1,100"));
        assertEquals(1, StringCalculator.add("1,101"));
        assertEquals(0, StringCalculator.add("123"));
    }
}
