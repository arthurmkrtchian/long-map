package de.comparus.opensource.longmap;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class LongMapPerformanceTest {

    static int ENTRIES_COUNT = 20_000_000;
    @Rule
    public JUnitPerfRule perfTestRule = new JUnitPerfRule(
            true,
            new ConsoleReportGenerator()
    );
    LongMap<Integer> emptyLongMap;
    LongMap<Integer> preFilledLongMap;

    public LongMapPerformanceTest() {
        this.emptyLongMap = new LongMapImpl<>();
    }

    @Before
    public void setUp() {
        this.emptyLongMap.clear();
        this.preFilledLongMap = new LongMapImpl<>();
        for (long key = 0; key < ENTRIES_COUNT; key++) {
            preFilledLongMap.put(key, (int) key);
        }
    }

    @Test
    @JUnitPerfTest
    public void testPut() {
        for (long key = 0; key < ENTRIES_COUNT; key++) {
            emptyLongMap.put(key, (int) key);
        }
    }

    @Test
    @JUnitPerfTest
    public void testGet() {
        long randomKey = (long) (Math.random() * ENTRIES_COUNT);
        int value = preFilledLongMap.get(randomKey);
    }

    @Test
    @JUnitPerfTest
    public void testRemove() {
        long randomKey = (long) (Math.random() * ENTRIES_COUNT);
        int value = preFilledLongMap.remove(randomKey);
    }

    @Test
    @JUnitPerfTest
    public void testIsEmpty() {
        preFilledLongMap.isEmpty();
    }

    @Test
    @JUnitPerfTest
    public void testContainsKey() {
        long randomKey = (long) (Math.random() * ENTRIES_COUNT * 1.2);
        preFilledLongMap.containsKey(randomKey);
    }

    @Test
    @JUnitPerfTest
    public void testContainsValue() {
        int randomValue = (int) (Math.random() * ENTRIES_COUNT * 1.2);
        preFilledLongMap.containsKey(randomValue);
    }

    @Test
    @JUnitPerfTest
    public void testKeys() {
        long[] keys = preFilledLongMap.keys();
    }

    @Test
    @JUnitPerfTest
    public void testValues() {
        List<Integer> values = preFilledLongMap.values();
    }

    @Test
    @JUnitPerfTest
    public void testSize() {
        preFilledLongMap.size();
    }

    @Test
    @JUnitPerfTest
    public void testClear() {
        preFilledLongMap.clear();
    }
}