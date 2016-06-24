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

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to build and manipulate map implementations.
 *
 * @author Grégory Van den Borre
 */
public interface Maps {

    /**
     * Create a new map, as for every components of this collection, it does not
     * accept any <code>null</code> value.
     *
     * @return The new map.
     */
    static <K, V> Map<K, V> newMap() {
        return new HashMap<>();
    }


    /**
     * Create a new bidirectional map, as for every components of this
     * collection, it does not accept any <code>null</code> value.
     *
     * @return The new bidirectional map.
     */
    static <K, V> BidirectionalMap<K, V> newBidirectionalMap() {
        return new BidirectionalMap<K, V>();
    }

}
