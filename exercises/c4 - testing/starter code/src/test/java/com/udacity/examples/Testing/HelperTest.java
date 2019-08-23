package com.udacity.examples.Testing;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelperTest {

    @Test
    public void test() {
        assertEquals("2", "2");
    }

    @Test
    public void verify_getCount() {
        List<String> names = Arrays.asList("Sareeta", "Udacity", "Github");
        long count = Helper.getCount(names);
        assertEquals(3, count);
    }

    @Test
    public void verify_mergeList() {
        List<String> names = Arrays.asList("Sareeta", "Udacity", "Github");
        String merged = Helper.getMergedList(names);
        assertEquals("Sareeta, Udacity, Github", merged);
    }
}
