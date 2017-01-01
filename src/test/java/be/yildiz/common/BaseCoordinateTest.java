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

package be.yildiz.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public class BaseCoordinateTest {

    @Test
    public void test() {
        Assert.assertEquals(0, BaseCoordinate.ZERO.width);
        Assert.assertEquals(0, BaseCoordinate.ZERO.height);
        Assert.assertEquals(0, BaseCoordinate.ZERO.left);
        Assert.assertEquals(0, BaseCoordinate.ZERO.top);
    }

    @Test
    public void testConstructor() {
        int top = 5;
        int left = 10;
        int width = 12;
        int height = 9;
        BaseCoordinate bc = new BaseCoordinate(width, height, left, top);
        Assert.assertEquals(top, bc.top);
        Assert.assertEquals(left, bc.left);
        Assert.assertEquals(width, bc.width);
        Assert.assertEquals(height, bc.height);
    }

    @Test
    public void testContains() {
        int top = 5;
        int left = 10;
        int width = 12;
        int height = 9;
        BaseCoordinate bc = new BaseCoordinate(width, height, left, top);
        BaseCoordinate bc2 = new BaseCoordinate(10, 7, 11, 6);
        Assert.assertTrue(bc.contains(bc2));
        bc2 = new BaseCoordinate(width, height, left, top);
        Assert.assertTrue(bc.contains(bc2));
        bc2 = new BaseCoordinate(width + 1, height, left, top);
        Assert.assertFalse(bc.contains(bc2));
        bc2 = new BaseCoordinate(width, height + 1, left, top);
        Assert.assertFalse(bc.contains(bc2));
    }

    @Test
    public void testEquals() {
        int top = 5;
        int left = 10;
        int width = 12;
        int height = 9;
        BaseCoordinate bc = new BaseCoordinate(width, height, left, top);
        BaseCoordinate bc2 = new BaseCoordinate(width, height, left, top);
        Assert.assertEquals(bc, bc2);
        Assert.assertFalse(bc.equals(null));
        Assert.assertFalse(bc.equals(new BaseCoordinate(width + 1, height, left, top)));
        Assert.assertFalse(bc.equals(new BaseCoordinate(width, height + 1, left, top)));
        Assert.assertFalse(bc.equals(new BaseCoordinate(width, height, left + 1, top)));
        Assert.assertFalse(bc.equals(new BaseCoordinate(width, height, left, top + 1)));
        Assert.assertFalse(bc.equals("abc"));
    }

    @Test
    public void testToString() {
        int top = 5;
        int left = 10;
        int width = 12;
        int height = 9;
        BaseCoordinate bc = new BaseCoordinate(width, height, left, top);
        Assert.assertEquals("Width:" + 12 + ", height:" + 9 + ", left:" + 10 + ", top:" + 5, bc.toString());
    }

}
