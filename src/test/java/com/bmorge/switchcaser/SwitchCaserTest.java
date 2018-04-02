package com.bmorge.switchcaser;


import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.testng.Assert.*;

public class SwitchCaserTest {

    private static final String TEST_SUCCESS = "test success";

    @Test
    public void test001_simpleTest_default() {
        AtomicBoolean switchIsWorck = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onCase(3)
                .onBreak(() -> switchIsWorck.set(false))
                .onDefault(() -> switchIsWorck.set(true));
        assertTrue(switchIsWorck.get());
    }

    @Test
    public void test002_simpleTest_notBreak() {
        AtomicBoolean switchIsWork = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onCase(3)
                .onBreak(() -> switchIsWork.set(true));
        assertFalse(switchIsWork.get());
    }

    @Test
    public void test003_simpleTest_oneCase() {
        AtomicBoolean switchIsWork = new AtomicBoolean(false);
        SwitchCaser.switchIt(1)
                .onCase(new Integer(1))
                .onBreak(() -> switchIsWork.set(true));
        assertTrue(switchIsWork.get());
    }

    @Test
    public void test004_simpleTest_validBreakInTheMiddle() {
        AtomicReference<TestContainer> container = new AtomicReference<>();
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onBreak(() -> container.set(new TestContainer("bar")))
                .onCase(1)
                .onBreak(() -> container.set(new TestContainer(TEST_SUCCESS)))
                .onCase(3)
                .onBreak(() -> container.set(new TestContainer("foo")));

        assertEquals(container.get().getString(), TEST_SUCCESS);
    }

    @Test
    public void test005_simpleTest_validBreakInTheEnd() {
        AtomicReference<TestContainer> container = new AtomicReference<>();
        SwitchCaser.switchIt(1)
                .onCase(2)
                .onBreak(() -> container.set(new TestContainer("bar")))
                .onCase(3)
                .onBreak(() -> container.set(new TestContainer("foo")))
                .onCase(1)
                .onBreak(() -> container.set(new TestContainer(TEST_SUCCESS)));

        assertEquals(container.get().getString(), TEST_SUCCESS);
    }

    static class TestContainer {
        private final String string;

        public TestContainer(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

}
