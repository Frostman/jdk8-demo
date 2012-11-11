package ru.frostman.jdk8.demo.lambda;

import org.junit.Test;

import java.util.functions.Mapper;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class WeirdFunctionTest {
    @Test
    public void test0() {
        Mapper<Integer, Integer> f = ((Integer) 0)::compareTo;
        Mapper<Integer, Integer> f1 = f.compose(f);

        // f1 does what?!
    }

    @Test
    public void test1() {
        Mapper<Integer, Integer> f = ((Integer) 0)::compareTo;
        f = f.compose(f);

        assertEquals(Integer.valueOf(0), f.map(0));
        assertEquals(Integer.valueOf(1), f.map(100));
        assertEquals(Integer.valueOf(-1), f.map(-100));
    }

    @Test
    public void test2() {
        Mapper<Integer, Integer> f = ((Mapper<Integer, Integer>) ((Integer) 0)::compareTo).compose(((Integer) 0)::compareTo);

        assertEquals(Integer.valueOf(0), f.map(0));
        assertEquals(Integer.valueOf(1), f.map(100));
        assertEquals(Integer.valueOf(-1), f.map(-100));
    }
}
