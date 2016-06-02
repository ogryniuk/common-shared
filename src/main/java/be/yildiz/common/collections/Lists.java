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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Utility class to build and manipulate list implementations.
 * @author Grégory Van den Borre
 */
public interface Lists {

    /**
     * Build a new List with NoNullArrayList implementation and set a collection
     * of objects inside.
     * @param values
     *            Collection of objects placed in the list.
     * @return The new list.
     */
    static List < Float > fromFloat(final float... values) {
        List < Float > list = new ArrayList < Float >(values.length);
        for (float f : values) {
            list.add(Float.valueOf(f));
        }
        return list;
    }

    /**
     * Build a new List with NoNullArrayList implementation.
     * @param <T>
     *            Contained objects type.
     * @return The new list.
     */
    static < T > List < T > newList() {
        return new ArrayList < T >();
    }

    /**
     * Build a new List with NoNullArrayList implementation and set a collection
     * of objects inside.
     * @param values
     *            Collection of objects placed in the list.
     * @return The new list.
     */
    static List < Integer > newList(final int... values) {
        List < Integer > list = new ArrayList < Integer >(values.length);
        for (int f : values) {
            list.add(Integer.valueOf(f));
        }
        return list;
    }

    /**
     * Build a new List with NoNullArrayList implementation and a given size.
     * @param <T>
     *            Contained objects type.
     * @param size
     *            List initial size.
     * @return The new list.
     */
    static < T > List < T > newList(final int size) {
        return new ArrayList < T >(size);
    }

    /**
     * Build a new List with NoNullArrayList implementation and set a collection
     * of objects inside.
     * @param <T>
     *            Contained objects type.
     * @param t
     *            Collection of objects placed in the list.
     * @return The new list.
     */
    @SuppressWarnings("unchecked")
    static < T > List < T > newList(final T... t) {
        return new ArrayList < T >(Arrays.asList(t));
    }

    static < T > List < T > newList(final Collection < T > c) {
        return new ArrayList < T >(c);
    }

    /**
     * Create a new List being a copy of the one provided. The copy is light,
     * only references are copied, changes in object in original list will be
     * reflected in the copy.
     * @param list
     *            List to copy.
     * @return The copy of the given list.
     */
    static < T > List < T > copy(List < T > list) {
        return Lists.newList(list);
    }
}
