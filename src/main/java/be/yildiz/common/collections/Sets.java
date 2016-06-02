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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Utility class to build and manipulate set implementations.
 * @author Grégory Van den Borre
 */
public interface Sets {

    /**
     * Build a new Set with LinkedHashSet implementation, order insertion is
     * preserved.
     * @param <T>
     *            Contained objects type.
     * @return The new set.
     */
    static < T > Set < T > newInsertionOrderedSet() {
        return new LinkedHashSet < T >();
    }

    /**
     * Build a new Set with TreeSet implementation, order is following the
     * natural objects order. For insertion ordered, see newInsertionOrderedSet.
     * @param <T>
     *            Contained objects type.
     * @return The new set.
     */
    static < T > Set < T > newOrderedSet() {
        return new TreeSet < T >();
    }

    /**
     * Build a new Set with HashSet implementation, order insertion is not
     * garanteed, use newOrderSet or newInsersionOrderedSet for that case.
     * @param <T>
     *            Contained objects type.
     * @return The new set.
     */
    static < T > Set < T > newSet() {
        return new HashSet < T >();
    }

    /**
     * Build a new Set with HashSet implementation from an existing array of
     * objects.
     * @param <T>
     *            Contained objects type.
     * @param values
     *            Objects to store in the created Set.
     * @return The new set.
     */
    @SuppressWarnings("unchecked")
    static < T > Set < T > newSet(final T... values) {
        Set < T > set = new HashSet<>(values.length);
        Collections.addAll(set, values);
        return set;
    }
    
    static < T > Set < T > newSet(final Collection<T> values) {
        return new HashSet<>(values);
    }
}
