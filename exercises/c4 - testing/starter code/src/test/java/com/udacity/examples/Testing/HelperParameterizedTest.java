package com.udacity.examples.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class HelperParameterizedTest {

    private String input;
    private String output;

    public HelperParameterizedTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection initData() {
        String[][] employees = {
                {"sareeta", "sareeta2"},
                {"sareeta", "Jeff"}
        };

        return Arrays.asList(employees);
    }

    @Test
    public void verify_input_name_is_not_same_as_output_name() {
        assertNotEquals(input, output);
    }
}
