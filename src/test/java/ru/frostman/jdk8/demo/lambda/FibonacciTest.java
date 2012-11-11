package ru.frostman.jdk8.demo.lambda;

import org.junit.Test;

import java.util.functions.IntUnaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class FibonacciTest {

//    public interface IntUnaryOperator {
//        public int operate(int operand);
//    }

    private IntUnaryOperator fib =
            (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);

    @Test
    public void testFib10() {
        assertEquals(55, fib.operate(10));
    }

    private static IntUnaryOperator fib_static =
            (n) -> (n < 2) ? n : fib_static.operate(n - 1) + fib_static.operate(n - 2);

    @Test
    public void testFib10Static() {
        assertEquals(55, fib_static.operate(10));
    }
}
