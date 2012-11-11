package ru.frostman.jdk8.demo.stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class SeqParTest {
    private static final int COUNT = Integer.getInteger("count", 1_000_000);

    private List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList<>(COUNT);
        for (int c = 1; c <= COUNT; c++) {
            list.add(c);
        }
        Collections.shuffle(list);
    }

    @Test
    public void testSeq() {
        Assert.assertEquals(
                Integer.valueOf(COUNT),
                list.stream().reduce(Math::max).get()
        );
    }

    @Test
    public void testPar() {
        Assert.assertEquals(
                Integer.valueOf(COUNT),
                list.parallel().reduce(Math::max).get()
        );
    }
}
