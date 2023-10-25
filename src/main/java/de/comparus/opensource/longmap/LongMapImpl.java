package de.comparus.opensource.longmap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;


public class LongMapImpl<V> implements LongMap<V> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final double LOAD_FACTOR = 0.8;
    private int size;
    private int capacity;
    private Entry<V>[] table;


    public LongMapImpl(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public LongMapImpl() {
        this(DEFAULT_CAPACITY);
    }

    public V put(long key, V value) {
        if (size > (table.length - 1) * LOAD_FACTOR) {
            resize();
        }

        int position = hash(key);
        Entry<V> entry = table[position];
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                entry.value = value;
                return value;
            }
        }

        Entry<V> newEntry = new Entry<>(key, value);
        newEntry.next = table[position];
        table[position] = newEntry;
        size++;

        return table[position].value;
    }

    public V get(long key) {
        int position = hash(key);
        Entry<V> entry = table[position];
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(long key) {
        int index = hash(key);
        Entry<V> entry = table[index];
        Entry<V> prev = null;
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
        }
        return prev != null ? prev.value : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        int index = hash(key);
        Entry<V> entry = table[index];
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (Entry<V> entry : table) {
            for (; entry != null; entry = entry.next) {
                if (Objects.equals(entry.value, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public long[] keys() {
        long[] keys = new long[size];
        int newPosition = 0;
        for (Entry<V> entry : table) {
            for (; entry != null; entry = entry.next) {
                keys[newPosition] = entry.key;
                newPosition++;
            }
        }
        return keys;
    }

    public V[] values() {
        Class<?> type = getType();

        if (type == null) {
            return (V[]) new Object[size];
        } else {
            V[] values = (V[]) Array.newInstance(type, size);
            int newPosition = 0;
            for (Entry<V> entry : table) {
                for (; entry != null; entry = entry.next) {
                    values[newPosition] = entry.value;
                    newPosition++;
                }
            }
            return values;
        }
    }

    private Class<?> getType() {
        for (Entry<V> entry : table) {
            for (; entry != null; entry = entry.next) {
                if (entry.value != null) {
                    return entry.value.getClass();
                }
            }
        }
        return null;
    }

    public long size() {
        return size;
    }

    public void clear() {
        if (table != null) {
            size = 0;
            Arrays.fill(table, null);
        }
    }

    private void resize() {
        this.capacity = table.length * 2;
        Entry<V>[] oldTable = table;
        table = new Entry[capacity];
        size = 0;
        for (Entry<V> entry : oldTable) {
            for (; entry != null; entry = entry.next) {
                put(entry.key, entry.value);
            }
        }
    }

    private int hash(long key) {
        return Long.hashCode(key) % capacity;
    }

    private static class Entry<V> {
        final long key;
        V value;
        Entry<V> next;

        Entry(long key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}