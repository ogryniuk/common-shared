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

package be.yildiz.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public class ColorTest {

    /***/
    @Test
    public final void testAdd() {
        Color base = new Color(10, 20, 30, 40);
        Color base2 = base.add(10);
        Assert.assertEquals(new Color(20, 30, 40, 40), base2);
    }

    /***/
    @Test
    public final void testEqualsObject() {
        Assert.assertTrue(Color.BLACK.equals(Color.BLACK));
        Assert.assertTrue(Color.BLACK.equals(new Color(0, 0, 0)));
        Assert.assertFalse(Color.BLACK.equals(null));
        Assert.assertFalse(Color.BLACK.equals("aString"));
        Assert.assertFalse(Color.BLACK.equals(new Color(1, 0, 0)));
        Assert.assertFalse(Color.BLACK.equals(new Color(0, 1, 0)));
        Assert.assertNotEquals(Color.BLACK, new Color(0, 0, 1));
        Assert.assertEquals(Color.BLACK, Color.BLACK);
        Assert.assertEquals(Color.BLACK, new Color(0, 0, 0, 255));
        Assert.assertNotEquals(Color.BLACK, new Color(0, 0, 0, 0));
    }

    /***/
    @Test
    public final void testHashCode() {
        Assert.assertEquals(Color.BLACK.hashCode(), new Color(0, 0, 0).hashCode());
    }

    /***/
    @Test
    public final void testToString() {
        Color c = Color.BLACK;
        Assert.assertEquals("0,0,0," + Color.MAX_VALUE, c.toString());
    }

    /***/
    @Test
    public final void testYzColorIntIntInt() {
        final int value = 5;
        final int tooHighValue = 1000;
        final int tooLowValue = -5;
        Color ac = new Color(value);
        Assert.assertEquals(value, ac.red);
        Assert.assertEquals(value, ac.blue);
        Assert.assertEquals(value, ac.green);
        Assert.assertEquals(Color.MAX_VALUE, ac.alpha);

        Color c = new Color(value, value + 2, value - 2);
        Assert.assertEquals(value, c.red);
        Assert.assertEquals(value - 2, c.blue);
        Assert.assertEquals(value + 2, c.green);
        c = new Color(tooHighValue, tooHighValue, tooHighValue);
        Assert.assertEquals(Color.MAX_VALUE, c.red);
        Assert.assertEquals(Color.MAX_VALUE, c.blue);
        Assert.assertEquals(Color.MAX_VALUE, c.green);
        c = new Color(tooLowValue, tooLowValue, tooLowValue);
        Assert.assertEquals(Color.MIN_VALUE, c.red);
        Assert.assertEquals(Color.MIN_VALUE, c.blue);
        Assert.assertEquals(Color.MIN_VALUE, c.green);
    }

}
