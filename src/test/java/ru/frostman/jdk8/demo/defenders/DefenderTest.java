package ru.frostman.jdk8.demo.defenders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * based on https://github.com/shipilev/jdk8-lambda-samples
 *
 * @author slukjanov
 */
public class DefenderTest {

    @Test
    public void testNew() {
        assertEquals("legacy method from new class", new NewClass().legacyMethod());
        assertEquals("new method from new class", new NewClass().newMethod());
    }

    @Test
    public void testLegacy() {
        assertEquals("legacy method from legacy class", new LegacyClass().legacyMethod());
        assertEquals("new method from legacy class", new LegacyClass().newMethod());
    }

    public interface LegacyInterface {
        String legacyMethod();

        String newMethod() default {
            return "new method from legacy class";
        }
    }

    public class LegacyClass implements LegacyInterface {
        @Override
        public String legacyMethod() {
            return "legacy method from legacy class";
        }
    }

    public class NewClass implements LegacyInterface {
        @Override
        public String legacyMethod() {
            return "legacy method from new class";
        }

        @Override
        public String newMethod() {
            return "new method from new class";
        }
    }

}
