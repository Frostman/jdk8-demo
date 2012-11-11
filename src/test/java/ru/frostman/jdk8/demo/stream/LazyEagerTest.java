package ru.frostman.jdk8.demo.stream;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.streams.Stream;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class LazyEagerTest {
    private int invocations = 0;

    @Test
    public void testLazy() {
        Stream<String> stream = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> {
                    invocations++;
                    return s.length() == 3;
                });

        Iterator<String> i = stream.iterator();

        Assert.assertEquals("Foo", i.next());
        Assert.assertEquals(1, invocations);

        Assert.assertEquals("Bar", i.next());
        Assert.assertEquals(3, invocations);

        Assert.assertEquals("Baz", i.next());
        Assert.assertEquals(5, invocations);
    }

    @Test
    public void testShortCircuit() {
        Stream<String> stream = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> {
                    invocations++;
                    return s.length() == 3;
                });

        Assert.assertEquals("Foo", stream.findFirst().get());
        Assert.assertEquals(1, invocations);

        Assert.assertEquals("Bar", stream.findFirst().get());
        Assert.assertEquals(3, invocations);

        Assert.assertEquals("Baz", stream.findFirst().get());
        Assert.assertEquals(5, invocations);
    }

    @Test
    public void testEager() {
        List<String> list = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> {
                    invocations++;
                    return s.length() == 3;
                })
                .into(new ArrayList<>());

        Assert.assertEquals(3, list.size());
        Assert.assertEquals(5, invocations);
    }
}
