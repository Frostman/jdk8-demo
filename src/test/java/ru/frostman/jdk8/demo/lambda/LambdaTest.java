package ru.frostman.jdk8.demo.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.functions.Block;
import java.util.functions.Factory;
import java.util.functions.Mapper;
import java.util.functions.Predicate;

import static junit.framework.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class LambdaTest {

    public static void checkCmp(Comparator<Integer> cmp) {
        assertEquals(0, cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1, cmp.compare(100, -100));
    }

    @Test
    public void testCmpLegacy() {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? -1 : ((x > y) ? 1 : 0);
            }
        };

        checkCmp(cmp);
    }

    @Test
    public void testCmpLambda0() {
        Comparator<Integer> cmp = (Integer x, Integer y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        checkCmp(cmp);
    }

    @Test
    public void testCmpLambda1() {
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        checkCmp(cmp);
    }

//    public interface Factory<T> {
//        T make();
//    }

    @Test
    public void testNoArgs() {
        Factory<Integer> factory = () -> 42;

        assertEquals(Integer.valueOf(42), factory.make());
    }

//    public interface Mapper<T, R> {
//        R map(T t);
//    }

    private void checkMap(Mapper<String, Integer> mapper) {
        assertEquals(Integer.valueOf(42), mapper.map("42"));
        assertEquals(Integer.valueOf(-1), mapper.map("-1"));
    }

    @Test
    public void testOneArg0() {
        Mapper<String, Integer> mapper = (str) -> Integer.parseInt(str);

        checkMap(mapper);
    }

    @Test
    public void testOneArg1() {
        Mapper<String, Integer> mapper = str -> Integer.parseInt(str);

        checkMap(mapper);
    }

    @Test
    public void testCmpLambda2() { // wrong
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);

        checkCmp(cmp);
    }

    @Test
    public void testCmpLambda3() { // right
        Comparator<Integer> cmp = (a, b) -> {
            int x = a;
            int y = b;
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        };

        checkCmp(cmp);
    }

//    public interface Block<T> {
//        void apply(T t);
//    }

    @Test
    public void testBlock0() {
        Block<String> b = s -> {
            System.out.println(s);
        };

        Arrays.asList("One", "Two", "Three", "Four", "Five").forEach(b);
    }

    @Test
    public void testBlock1() {
        Block<String> b = s -> System.out.println(s);

        Arrays.asList("One", "Two", "Three", "Four", "Five").forEach(b);
    }

//    public interface Predicate<T> {
//        boolean test(T t);
//    }

    @Test
    public void testPredicate() {
        Predicate<Integer> predicate = (x) -> x == 42;

        assertEquals(true, predicate.test(42));
        assertEquals(false, predicate.test(13));
        assertEquals(false, predicate.test(666));
    }

}
