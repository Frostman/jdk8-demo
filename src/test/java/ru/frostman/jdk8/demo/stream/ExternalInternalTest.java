package ru.frostman.jdk8.demo.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class ExternalInternalTest {

    @Test
    public void testExternal() {
        StringBuilder sb = new StringBuilder();

        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        for (String s : strings) {
            sb.append(s);
        }

        assertEquals(
                "FooBarBaz",
                sb.toString()
        );
    }

    @Test
    public void testInternal() {
        StringBuilder sb = new StringBuilder();

        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        strings.forEach(sb::append);

        assertEquals(
                "FooBarBaz",
                sb.toString()
        );
    }

}
