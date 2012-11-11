package ru.frostman.jdk8.demo.lambda;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.functions.Factory;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class ThreadLocalTest {

    @Test
    public void testThreadLocalLegacy() {
        final AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return counter.incrementAndGet();
            }
        };

        assertEquals(tlNumber.get(), (Integer) 1);
        assertEquals(tlNumber.get(), (Integer) 1);
    }

    // FIXME: javac is unable to infer ThreadLocal type
    @Test
    public void testThreadLocalLambda() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = new ThreadLocal<>(() -> counter.incrementAndGet());

        assertEquals(tlNumber.get(), (Integer) 1);
        assertEquals(tlNumber.get(), (Integer) 1);
    }

    @Test
    public void testThreadLocalMRef() {
        AtomicInteger counter = new AtomicInteger(0);
        Factory<Integer> factory = counter::incrementAndGet;
        ThreadLocal<Integer> tlNumber = new ThreadLocal<>(factory);

        assertEquals(tlNumber.get(), (Integer) 1);
        assertEquals(tlNumber.get(), (Integer) 1);
    }

}
