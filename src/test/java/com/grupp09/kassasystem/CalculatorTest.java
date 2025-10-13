package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void add_TwoNumbers_GivesCorrectResult() {
        // Arrange
        var num1 = 3.5;
        var num2 = 2.0;
        var expectedValue = 5.5;

        // Act
        var sum = Calculator.Add(num1, num2);

        //Assert
        assertEquals(expectedValue, sum);        
    }

}