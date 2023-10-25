package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final double LOAD_FACTOR = 0.8;
    private int size;
    private List<Entry<V>> table;

    public LongMapImpl(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    public LongMapImpl() {
        this(DEFAULT_CAPACITY);
    }

    public V put(long key, V value) {
        if (size > (table.size() - 1) * LOAD_FACTOR) {
            resize();
        }
        int position = hash(key);
        Entry<V> entry = table.get(position);
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                entry.value = value;
                return value;
            }
        }

        Entry<V> newEntry = new Entry<>(key, value);
        newEntry.next = table.get(position);
        table.set(position, newEntry);
        size++;
        return table.get(position).value;
    }

    public V get(long key) {
        int position = hash(key);
        Entry<V> entry = table.get(position);
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(long key) {
        int index = hash(key);
        Entry<V> entry = table.get(index);
        Entry<V> prev = null;
        for (; entry != null; entry = entry.next) {
            if (entry.key == key) {
                if (prev == null) {
                    table.set(index, entry.next);
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
        Entry<V> entry = table.get(index);
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
                keys[newPosition++] = entry.key;
            }
        }
        return keys;
    }

    public List<V> values() {
        List<V> valueList = new ArrayList<>();
        for (Entry<V> entry : table) {
            for (; entry != null; entry = entry.next) {
                valueList.add(entry.value);
            }
        }
        return valueList;
    }

    public long size() {
        return size;
    }

    public void clear() {
        Collections.fill(table, null);
        size = 0;
    }

    private void resize() {
        int newCapacity = table.size() * 2;
        List<Entry<V>> oldTable = table;
        table = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            table.add(null);
        }
        size = 0;
        for (Entry<V> entry : oldTable) {
            for (; entry != null; entry = entry.next) {
                put(entry.key, entry.value);
            }
        }
    }

    private int hash(long key) {
        return Long.hashCode(key) % table.size();
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
