/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.common.collections;

import java.util.*;
import java.util.Map.Entry;

/**
 * Wrap 2 Maps to easily get a value from a key and key a key from a value. The
 * mapping must be 1 - 1, only one value for a key.
 *
 * @param <K> Type for the keys.
 * @param <V> Type for the values.
 * @author Grégory Van den Borre
 */
public final class BidirectionalMap<K, V> implements Iterable<Entry<K, V>> {

    /**
     * For error messages.
     */
    private static final String NULL_NOT_ALLOWED = "null value not allowed.";

    /**
     * Base map.
     */
    private final Map<K, V> map = new HashMap<>();

    /**
     * Revert map, to get a key from its value.
     */
    private final Map<V, K> inverseMap = new HashMap<>();

    /**
     * Retrieve a key from a value.
     *
     * @param value Stored value.
     * @return Associated key.
     */
    public K getKey(final Object value) {
        if (value == null) {
            throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        }
        return this.inverseMap.get(value);
    }

    /**
     * Get a key from a value, or return the given one.
     *
     * @param value            Value matching the key.
     * @param replacementValue Value to return in case if the given does not match the key.
     * @return The matching key, or the replacement value.
     */
    public K getKeyOr(final V value, final K replacementValue) {
        if (value == null) {
            throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        }
        if (replacementValue == null) {
            throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        }
        return this.inverseMap.getOrDefault(value, replacementValue);
    }

    /**
     * Retrieve the value from the key.
     *
     * @param key Stored key.
     * @return Associated value.
     */
    public V getValue(final K key) {
        return this.map.get(key);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return this.map.entrySet().iterator();
    }

    /**
     * Add a key and its value to the map.
     *
     * @param key   The map key.
     * @param value The value associated to the key.
     */
    public void put(final K key, final V value) {
        this.map.put(key, value);
        this.inverseMap.put(value, key);
    }

    /**
     * Remove a key and its values from the containers.
     *
     * @param key Object to remove.
     */
    public void remove(final K key) {
        this.inverseMap.remove(this.map.remove(key));
    }

    public List<V> getValues() {
        return new ArrayList<>(this.map.values());
    }

    public List<K> getKeys() {
        return new ArrayList<>(this.inverseMap.values());
    }
}
