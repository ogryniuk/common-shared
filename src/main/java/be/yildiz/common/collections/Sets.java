//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.common.collections;

import java.util.*;

/**
 * Utility class to build and manipulate set implementations.
 *
 * @author Grégory Van den Borre
 */
public interface Sets {

    /**
     * Build a new Set with LinkedHashSet implementation, order insertion is
     * preserved.
     *
     * @param <T> Contained objects type.
     * @return The new set.
     */
    static <T> LinkedHashSet<T> newInsertionOrderedSet() {
        return new LinkedHashSet<>();
    }

    /**
     * Build a new Set with TreeSet implementation, order is following the
     * natural objects order. For insertion ordered, see newInsertionOrderedSet.
     *
     * @param <T> Contained objects type.
     * @return The new set.
     */
    static <T> TreeSet<T> newOrderedSet() {
        return new TreeSet<>();
    }

    /**
     * Build a new Set with HashSet implementation, order insertion is not
     * garanteed, use newOrderSet or newInsersionOrderedSet for that case.
     *
     * @param <T> Contained objects type.
     * @return The new set.
     */
    static <T> HashSet<T> newSet() {
        return new HashSet<>();
    }

    /**
     * Build a new Set with HashSet implementation from an existing array of
     * objects.
     *
     * @param <T>    Contained objects type.
     * @param values Objects to store in the created Set, null values are not allowed.
     * @return The new set.
     */
    @SafeVarargs
    static <T> HashSet<T> newSet(final T... values) {
        HashSet<T> set = new HashSet<>(values.length);
        Collections.addAll(set, values);
        if(set.contains(null)) {
            throw new NullPointerException("Null value is not allowed.");
        }
        return set;
    }

    static <T> HashSet<T> newSet(final Collection<T> values) {
        if(values.contains(null)) {
            throw new NullPointerException("Null value is not allowed.");
        }
        return new HashSet<>(values);
    }
}
