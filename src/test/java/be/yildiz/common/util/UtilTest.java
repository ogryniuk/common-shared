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

package be.yildiz.common.util;

import be.yildiz.common.collections.CollectionUtil;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.collections.Sets;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author Grégory Van Den Borre
 */
public final class UtilTest {

    private static final float EPSILON = 0.0000000001f;

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    /***/
    @Test
    public void testConcatArray() {
        byte[] a1 = {1, 1, 1, 1};
        byte[] a2 = {2, 2, 2};

        byte[] result = CollectionUtil.concatByteArrays(a1, a2);
        Assert.assertEquals(7, result.length);
        byte[] expected = {1, 1, 1, 1, 2, 2, 2};
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(expected[i], result[i]);
        }
    }

    /***/
    @Test
    public void testEqualFloat() {
        float f1 = 5.00001f;
        float f2 = 549.654516545f;
        float result1 = f1 + f2;
        float result2 = f1 + f2;
        float f = result1 - result2;
        Assert.assertTrue(f == 0.0 || f > -UtilTest.EPSILON && f < UtilTest.EPSILON);
        Assert.assertTrue(Util.equalFloat(result1, result2));
        float t1 = result2 += 0.1f;
        float t2 = result1 -= 0.1f;
        Assert.assertFalse(Util.equalFloat(result1, t1));
        Assert.assertFalse(Util.equalFloat(t2, result2));
    }

    /**
     * Test method for {@link be.yildiz.common.util.Util#getRandom()}.
     */
    @Test
    public void testGetRandom() {
        final int testLength = 1000;
        int[] tab = new int[testLength];
        for (int i = 0; i < testLength; i++) {
            tab[i] = Util.getRandom();
        }
        for (int i = 0; i < testLength; i++) {
            for (int j = 0; j < testLength; j++) {
                if (i != j) {
                    Assert.assertFalse("comparing " + tab[i] + " and " + tab[j], tab[i] == tab[j]);
                }
            }
        }
    }

    /***/
    @Test
    public void testGreaterThanZero() {
        this.rule.expect(InvalidParameterException.class);
        Util.greaterThanZero(0);
        this.rule.expect(InvalidParameterException.class);
        Util.greaterThanZero(-5);
        try {
            Util.greaterThanZero(0.001f);
        } catch (InvalidParameterException e) {
            Assert.fail("Should not have thrown invalid parameter exception.");
        }
    }

    /**
     * Check the insertion order is preserved.
     */
    @Test
    public void testNewInsertionOrderSet() {
        Set<Integer> list = Sets.newInsertionOrderedSet();
        Assert.assertTrue(list instanceof LinkedHashSet<?>);
        Integer[] a = new Integer[50];
        for (int i = 0; i < a.length; i++) {
            a[i] = Util.getRandom();
            list.add(a[i]);
        }
        Iterator<Integer> it = list.iterator();
        for (Integer element : a) {
            Assert.assertEquals(element, it.next());
        }
    }

    /***/
    @Test
    public void testNewList() {
        List<Integer> list = Lists.newList();
        Assert.assertTrue(list instanceof ArrayList<?>);
        list = Lists.newList(43);
        Assert.assertTrue(list instanceof ArrayList<?>);
        Integer[] a = new Integer[50];
        for (int i = 0; i < a.length; i++) {
            a[i] = Util.getRandom();
            list.add(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            Assert.assertEquals(a[i], list.get(i));
        }
    }
}
