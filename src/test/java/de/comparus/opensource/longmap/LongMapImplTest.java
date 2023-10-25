package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class LongMapImplTest {

    LongMap<String> longMap;

    @Before
    public void setUp() {
        this.longMap = new LongMapImpl<>();
        longMap.put(12L, "Json Statham");
        longMap.put(16L, "Angelina Javaly");
        longMap.put(18L, "Elon MusQL");
        longMap.put(23L, "Nicolas Coverage");
        longMap.put(23L, "Linus Codevalds");
    }

    @Test
    public void testPutEntries() {
        longMap.put(24L, "Nikola Teslanet");
        longMap.put(25L, "Sylvester Firewallone");
        longMap.put(26L, "Jean-Cloud Van Data");

        Assert.assertEquals(7, longMap.size());
    }

    @Test
    public void testGetExistingValue() {
        String expected = "Angelina Javaly";
        String actual = longMap.get(16L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetNotExistingValue() {
        String expected = "Angelina Javaly";
        String actual = longMap.get(101L);

        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void testRemoveEntryByKey() {
        longMap.remove(18L);

        Assert.assertFalse(longMap.containsKey(18L));
        Assert.assertFalse(longMap.containsValue("Elon MusQL"));
        Assert.assertEquals(3, longMap.size());
    }

    @Test
    public void testIsEmpty() {
        LongMap<String> longMap = new LongMapImpl<>(5);
        Assert.assertTrue(longMap.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        LongMap<String> longMap = new LongMapImpl<>(-5);
        longMap.put(155L, "Json Statham");

        Assert.assertFalse(longMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        Assert.assertTrue(longMap.containsKey(12L));
        Assert.assertTrue(longMap.containsKey(16L));
        Assert.assertTrue(longMap.containsKey(18L));
        Assert.assertTrue(longMap.containsKey(23L));
    }

    @Test
    public void testContainsValue() {
        Assert.assertTrue(longMap.containsValue("Json Statham"));
        Assert.assertTrue(longMap.containsValue("Angelina Javaly"));
        Assert.assertTrue(longMap.containsValue("Elon MusQL"));
        Assert.assertTrue(longMap.containsValue("Linus Codevalds"));

        long expected = 4;
        long actual = longMap.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllKeys() {
        long[] expected = new long[]{12L, 16L, 18L, 23L};

        long[] actual = longMap.keys();
        Arrays.sort(actual);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetAllValues() {
        String[] expected = new String[]{"Angelina Javaly", "Elon MusQL", "Json Statham", "Linus Codevalds"};

        String[] actual = longMap.values();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        long expected = 5;

        longMap.put(44L, "Json Mamoa");
        long actual = longMap.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testClear() {
        longMap.clear();
        Assert.assertEquals(0, longMap.size());
    }
}