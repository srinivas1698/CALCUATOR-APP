package edu.sjsu.android.project1srinivasraochavan;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void calculate_no_tax() {
        double expected=81.71;
        double actual = Calculator.calculate(10000,5.5,15,0.0);
        assertEquals(expected, actual, 0.01);
    }
    @Test
    public void calculate_no_interest_rate()
    {
        double expected=83.33;
        double actual=Calculator.calculate(20000,0,20,0.1);
        assertEquals(expected, actual,0.01);
    }
    @Test
    public void calculate_no_of_years20()
    {
        double expected=213.0;
        double actual=Calculator.calculate(20000,10.0,20,2000.0);
        assertEquals(expected, actual,0.01);
    }
}