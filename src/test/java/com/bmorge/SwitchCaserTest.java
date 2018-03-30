package com.bmorge;


import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SwitchCaserTest {

    @Test
    public void test001_simpleTest() {
        AtomicBoolean switchIsWorck = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onCase(3)
                .onBreak(() -> switchIsWorck.set(false))
                .onDefault(() -> switchIsWorck.set(true));
        assertTrue(switchIsWorck.get());
    }

    @Test
    public void test002_simpleTest(){
        AtomicBoolean switchIsWorck = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onCase(3)
                .onBreak(() -> switchIsWorck.set(true));
        assertFalse(switchIsWorck.get());
    }

    @Test
    public void test003_simpleTest(){
        AtomicBoolean switchIsWorck = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(new Integer(1))
                .onBreak(() -> switchIsWorck.set(true));
        assertTrue(switchIsWorck.get());
    }

    @Test
    public void test004_simpleTest(){
        AtomicReference<TestContainer> container = new AtomicReference<>();
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onBreak(() -> container.set(new TestContainer("bar")))
                .onCase(3)
                .onBreak(() -> container.set(new TestContainer("foo")))
                .onCase(1)
                .onBreak(() -> container.set(new TestContainer("test success")));

        assertEquals(container.get().getString(), "test success");
    }

    static class TestContainer{
        private final String string;

        public TestContainer(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

}
