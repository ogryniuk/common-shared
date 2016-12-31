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

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public final class ListsTest {

    @Test
    public void testFromFloat() {
        float[] floats = new float[]{2, 4, 9, 8};
        List<Float> list = Lists.fromFloat(floats);
        Assert.assertEquals(floats.length, list.size());
        for (int i = 0; i < floats.length; i++) {
            Assert.assertEquals(floats[i], list.get(i).floatValue(), 0.0001f);
        }
        try {
            list = Lists.fromFloat(null);
            Assert.fail();
        } catch (NullPointerException npe) {
            // ok
        }
    }

    @Test
    public void testInt() {
        List<Integer> l = Lists.fromInts(4,5,8,9);
        Assert.assertNotNull(l);
        Assert.assertEquals(4, l.size());
        Assert.assertTrue(l.contains(4));
        Assert.assertTrue(l.contains(5));
        Assert.assertTrue(l.contains(8));
        Assert.assertTrue(l.contains(9));
    }
}
