package ru.frostman.jdk8.demo.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.functions.Block;
import java.util.functions.Factory;
import java.util.functions.Mapper;
import java.util.functions.Predicate;

import static org.junit.Assert.*;
import static ru.frostman.jdk8.demo.lambda.LambdaTest.checkCmp;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class MethodRefTest {

    @Test
    public void testMethodRefStatic() {
        Comparator<Integer> cmp = Integer::compare;

        checkCmp(cmp);
    }

    @Test
    public void testMethodRefInstance0() {
        //  Block<String> b = s -> System.out.println(s);
        Block<String> b = System.out::println;
        Arrays.asList("One", "Two", "Three", "Four", "Five").forEach(b);
    }

    private Predicate<String> makeCaseInsensitiveMatcher(String pattern) {
        return pattern::equalsIgnoreCase;
    }

    @Test
    public void testMethodRefInstance1() {
        assertTrue(makeCaseInsensitiveMatcher("true").test("TRuE"));
        assertTrue(makeCaseInsensitiveMatcher("false").test("FaLSE"));
        assertFalse(makeCaseInsensitiveMatcher("true").test("FAlsE"));
    }

    @Test
    public void testMethodRefInstance2() {
        Predicate<String> isTrue = "true"::equalsIgnoreCase;

        assertTrue(isTrue.test("TRuE"));
        assertFalse(isTrue.test("FAlsE"));
    }

//    public interface Comparator<T> {
//        int compare(T o1, T o2);
//    }

//    public final class Integer extends Number implements Comparable<Integer> {
//        public int compareTo(Integer anotherInteger) { ... }
//    }

    @Test
    public void testMethodRefInstanceUnbound() {
        Comparator<Integer> cmp = Integer::compareTo;

        checkCmp(cmp);
    }


    @Test
    public void testParseInt() {
        Mapper<String, Integer> mapper = Integer::parseInt;

        assertEquals(Integer.valueOf(0), mapper.map("0"));
        assertEquals(Integer.valueOf(1), mapper.map("1"));
    }

    @Test
    public void testConstructor() {
        Mapper<String, Integer> f = Integer::new;

        assertEquals(Integer.valueOf(0), f.map("0"));
        assertEquals(Integer.valueOf(1), f.map("1"));
    }

    public static class Counter {
        private int count = 0;

        public Counter() {
            this(0);
        }

        public Counter(int count) {
            this.count = count;
        }

        public int inc() {
            return ++count;
        }

        public int get() {
            return count;
        }
    }

    @Test
    public void testConstructor0() {
        Factory<Counter> factory = Counter::new;

        assertEquals(0, factory.make().get());
        assertEquals(1, factory.make().inc());
    }

    @Test
    public void testConstructor1() {
        Mapper<Integer, Counter> factory = Counter::new;

        assertEquals(1, factory.map(1).get());
        assertEquals(42, factory.map(42).get());
    }

}
