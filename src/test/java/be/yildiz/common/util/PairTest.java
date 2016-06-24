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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public class PairTest {

    /**
     * Test method for {@link be.yildiz.common.util.Pair#getObject1()}.
     */
    @Test
    public final void testGetObject1() {
        Pair<String, Integer> p = new Pair<String, Integer>("test", 5);
        Assert.assertNotNull(p.getObject1());
        Assert.assertEquals(p.getObject1(), "test");
    }

    /**
     * Test method for {@link be.yildiz.common.util.Pair#getObject2()}.
     */
    @Test
    public final void testGetObject2() {
        Pair<String, Integer> p = new Pair<String, Integer>("test", 5);
        Assert.assertNotNull(p.getObject2());
        Assert.assertEquals(p.getObject2(), Integer.valueOf(5));
    }

    /**
     * Test method for
     * {@link be.yildiz.common.util.Pair#setObject1(java.lang.Object)}.
     */
    @Test
    public final void testSetObject1() {
        Pair<String, Integer> p = new Pair<String, Integer>("test", 5);
        p.setObject1("test2");
        Assert.assertNotNull(p.getObject1());
        Assert.assertEquals(p.getObject1(), "test2");
    }

    /**
     * Test method for
     * {@link be.yildiz.common.util.Pair#setObject2(java.lang.Object)}.
     */
    @Test
    public final void testSetObject2() {
        Pair<String, Integer> p = new Pair<String, Integer>("test", 5);
        p.setObject2(10);
        Assert.assertNotNull(p.getObject2());
        Assert.assertEquals(p.getObject2(), Integer.valueOf(10));
    }

}
