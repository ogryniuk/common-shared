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

import be.yildiz.common.util.Util;
import be.yildiz.common.vector.Point2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public final class RectangleTest {

    /***/
    private final int x1 = 5;
    /***/
    private final int x2 = 15;
    /***/
    private final int y1 = 12;
    /***/
    private final int y2 = 28;
    /***/
    private Rectangle r;

    /***/
    @Test
    public void containsTest() {

        Point2D p = new Point2D(6, 14);
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(this.r.contain(this.x1, this.y1));
        Assert.assertTrue(this.r.contain(this.x2, this.y2));
        Assert.assertTrue(this.r.contain(p));
        Assert.assertTrue(this.r.contain(5, 12));
        Assert.assertFalse(this.r.contain(16, 16));
        Assert.assertFalse(this.r.contain(4, 12));
        this.r.setValues(this.x2, this.y2, this.x1, this.y1);
        Assert.assertTrue(this.r.contain(p));
        Assert.assertTrue(this.r.contain(5, 12));
        Assert.assertFalse(this.r.contain(16, 16));
        Assert.assertFalse(this.r.contain(4, 12));
    }

    /***/
    @Test
    public void moveTest() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        this.r.move(0, 0);
        Rectangle r2 = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertEquals(this.r, r2);
        final int xMove = 10;
        final int yMove = 5;
        this.r.move(xMove, yMove);
        Assert.assertEquals(this.x1 + xMove, this.r.getLeft());
        Assert.assertEquals(this.x2 + xMove, this.r.getRight());
        Assert.assertEquals(this.y1 + yMove, this.r.getTop());
        Assert.assertEquals(this.y2 + yMove, this.r.getBottom());
    }

    @Test
    public void resetTest() {
        Rectangle r = new Rectangle(10, 10, 20, 20);
        r.reset();
        Assert.assertEquals(0, r.getLeft());
        Assert.assertEquals(0, r.getRight());
        Assert.assertEquals(0, r.getTop());
        Assert.assertEquals(0, r.getBottom());
        Assert.assertEquals(0, r.getWidth());
        Assert.assertEquals(0, r.getHeight());
    }

    /***/
    @Test
    public void testEquals() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(this.r.equals(this.r));
        Assert.assertFalse(this.r.equals(null));
        Assert.assertFalse(this.r.equals(Boolean.valueOf(true)));
        Rectangle r2 = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(this.r.equals(r2));
        r2.setValues(this.x1, this.x2, this.y1 + 5, this.y2);
        Assert.assertFalse(this.r.equals(r2));
    }

    /***/
    @Test
    public void testGetBottom() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getBottom(), this.y2));
    }

    /***/
    @Test
    public void testGetHeight() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getHeight(), this.y2 - this.y1));
        this.r = new Rectangle(this.x2, this.y2, this.x1, this.y1);
        Assert.assertTrue(Util.equalFloat(this.r.getHeight(), this.y2 - this.y1));
    }

    /***/
    @Test
    public void testGetLeft() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getLeft(), this.x1));
    }

    /***/
    @Test
    public void testGetRight() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getRight(), this.x2));
    }

    /***/
    @Test
    public void testGetTop() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getTop(), this.y1));
    }

    /***/
    @Test
    public void testGetWidth() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getWidth(), this.x2 - this.x1));
        this.r = new Rectangle(this.x2, this.y1, this.x1, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getWidth(), this.x2 - this.x1));
    }

    /***/
    @Test
    public void testHashCode() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Rectangle r2 = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertEquals(this.r.hashCode(), r2.hashCode());
    }

    /***/
    @Test
    public void testNormalize() {
        final int left = 4;
        final int right = 2;
        final int top = 41;
        final int bottom = 2;
        this.r = new Rectangle(left, top, right, bottom);
        Assert.assertTrue(this.r.getLeft() > this.r.getRight());
        Assert.assertTrue(this.r.getTop() > this.r.getBottom());
        this.r.normalize();
        Assert.assertTrue(this.r.getLeft() < this.r.getRight());
        Assert.assertTrue(this.r.getTop() < this.r.getBottom());
        this.r = new Rectangle(0, 0, 5, 5);
        this.r.normalize();
        Assert.assertTrue(this.r.getLeft() < this.r.getRight());
        Assert.assertTrue(this.r.getTop() < this.r.getBottom());
    }

    /***/
    @Test
    public void testSetValues() {
        final int left = 4;
        final int right = 9;
        final int top = 1;
        final int bottom = 55;
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getLeft(), this.x1));
        Assert.assertTrue(Util.equalFloat(this.r.getRight(), this.x2));
        Assert.assertTrue(Util.equalFloat(this.r.getTop(), this.y1));
        Assert.assertTrue(Util.equalFloat(this.r.getBottom(), this.y2));

        this.r.setValues(left, top, right, bottom);
        Assert.assertTrue(Util.equalFloat(this.r.getLeft(), left));
        Assert.assertTrue(Util.equalFloat(this.r.getRight(), right));
        Assert.assertTrue(Util.equalFloat(this.r.getTop(), top));
        Assert.assertTrue(Util.equalFloat(this.r.getBottom(), bottom));

    }

    /***/
    @Test
    public void testYzRectangle() {
        this.r = new Rectangle();
        Assert.assertTrue(this.r.getLeft() == 0.0f);
        Assert.assertTrue(this.r.getRight() == 0.0f);
        Assert.assertTrue(this.r.getTop() == 0.0f);
        Assert.assertTrue(this.r.getBottom() == 0.0f);
    }

    /***/
    @Test
    public void testYzRectangleFloatFloatFloatFloat() {
        this.r = new Rectangle(this.x1, this.y1, this.x2, this.y2);
        Assert.assertTrue(Util.equalFloat(this.r.getLeft(), this.x1));
        Assert.assertTrue(Util.equalFloat(this.r.getRight(), this.x2));
        Assert.assertTrue(Util.equalFloat(this.r.getTop(), this.y1));
        Assert.assertTrue(Util.equalFloat(this.r.getBottom(), this.y2));
    }
}
