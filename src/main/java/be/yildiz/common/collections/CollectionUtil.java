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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class to manipulate collections.
 *
 * @author Grégory Van den Borre
 */
public interface CollectionUtil {

    /**
     * Copy an array.
     *
     * @param arrayToCopy Array to copy.
     * @return the copy.
     */
    static float[] arrayCopy(final float[] arrayToCopy) {
        return Arrays.copyOf(arrayToCopy, arrayToCopy.length);
    }

    /**
     * Copy an array.
     *
     * @param arrayToCopy Array to copy.
     * @param <T>         Type of objects in array.
     * @return the copy.
     */
    static <T> T[] arrayCopy(final T[] arrayToCopy) {
        return Arrays.copyOf(arrayToCopy, arrayToCopy.length);
    }

    /**
     * Check that every float in the first array are bigger than the second at
     * the same index. i.e: a[3] = 4 and b[3] = 2 will return
     * <code>false</code>.
     *
     * @param a First array to check.
     * @param b Second array to check.
     * @return <code>true</code> if all element at same index of first array are
     * bigger than in second array.
     */
    static boolean checkBiggerOrEqual(final float[] a, final float[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fill an array with the content of another.
     *
     * @param destination Array to fill.
     * @param dataToCopy  Array to get data.
     */
    static void cloneArray(final float[] destination, final float[] dataToCopy) {
        System.arraycopy(dataToCopy, 0, destination, 0, destination.length);
    }

    /**
     * Concatenate 2 arrays of bytes.
     *
     * @param array1 The first array.
     * @param array2 The second array.
     * @return A new byte array containing the 2 arrays data.
     */
    static byte[] concatByteArrays(final byte[] array1, final byte[] array2) {
        byte[] result = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    static <K, V> Set<V> getOrCreateSetFromMap(Map<K, Set<V>> map,
                                               K key) {
        Set<V> result = map.get(key);
        if (result == null) {
            result = new HashSet<>();
            map.put(key, result);
        }
        return result;
    }
}
