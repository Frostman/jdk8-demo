package ru.frostman.jdk8.demo.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.functions.Block;
import java.util.streams.Stream;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class StreamApiTest {

    @Test
    public void test1() {
        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        Stream<String> stream = strings.stream();

        assertEquals(
                "Foo",
                stream.findFirst().get()
        );
    }

    @Test
    public void test2() {
        assertEquals(
                Arrays.asList("Bar", "Baz"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .filter((s) -> s.startsWith("B"))
                        .into(new ArrayList<String>())
        );
    }

    @Test
    public void test3() {
        assertEquals(
                Arrays.asList(3, 3, 3),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .map(String::length)
                        .into(new ArrayList<Integer>())
        );
    }

    @Test
    public void test4() {
        assertEquals(
                Integer.valueOf(9),
                Arrays.asList("Foo", "BarBar", "BazBazBaz")
                        .stream()
                        .map(String::length)
                        .reduce((l, r) -> (l > r ? l : r))
                        .get()
        );
    }

    @Test
    public void test5() {
        assertEquals(
                Arrays.asList("Foo", "Bar", "Baz"),
                Arrays.asList("Foo Bar Baz")
                        .stream()
                        .flatMap((Block<? super String> sink, String element)
                                -> Arrays.asStream(element.split(" ")).forEach(sink))
                        .into(new ArrayList<String>()));
    }

    @Test
    public void test6() {
        assertEquals(
                new ArrayList<String>() {{
                    add("Foo");
                    add("Bar");
                    add("Baz");
                }},
                Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar")
                        .stream()
                        .uniqueElements()
                        .into(new ArrayList<String>())
        );
    }

    @Test
    public void test7() {
        assertEquals(
                Arrays.asList("Bar", "Baz", "Foo"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .sorted(String::compareTo)
                        .into(new ArrayList<String>())
        );
    }

    @Test
    public void test8() {
        assertEquals(
                Arrays.asList("Foo", "FooBar", "FooBarBaz"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .cumulate((l, r) -> l + r)
                        .into(new ArrayList<String>())
        );
    }
}
