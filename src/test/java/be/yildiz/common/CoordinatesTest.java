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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public class CoordinatesTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    /**
     * Test method for
     * {@link be.yildiz.common.Coordinates#Coordinates(int, int, int, int)}.
     */
    @Test
    public void testCoordinatesIntIntIntInt() {
        BaseCoordinate c = new Coordinates(10, 15, 14, 16);
        Assert.assertEquals(10, c.width);
        Assert.assertEquals(15, c.height);
        Assert.assertEquals(14, c.left);
        Assert.assertEquals(16, c.top);
    }

    /**
     * Test method for
     * {@link be.yildiz.common.Coordinates#Coordinates(be.yildiz.common.Size, int, int)}
     * .
     */
    @Test
    public void testCoordinatesSizeIntInt() {
        BaseCoordinate c = new Coordinates(new Size(10, 15), 14, 16);
        Assert.assertEquals(10, c.width);
        Assert.assertEquals(15, c.height);
        Assert.assertEquals(14, c.left);
        Assert.assertEquals(16, c.top);
        this.rule.expect(NullPointerException.class);
        c = new Coordinates(null, 14, 16);
    }

    /**
     * Test method for
     * {@link be.yildiz.common.Coordinates#Coordinates(be.yildiz.common.Size, be.yildiz.common.Position)}
     * .
     */
    @Test
    public void testCoordinatesSizePosition() {
        BaseCoordinate c = new Coordinates(new Size(10, 15), new Position(14, 16));
        Assert.assertEquals(15, c.height);
        Assert.assertEquals(10, c.width);
        Assert.assertEquals(14, c.left);
        Assert.assertEquals(16, c.top);
        this.rule.expect(NullPointerException.class);
        c = new Coordinates(new Size(10, 15), null);
    }

    /**
     * Test method for {@link be.yildiz.common.Coordinates#clone()}.
     */
    @Test
    public void testClone() {
        Coordinates c = new Coordinates(new Size(10, 15), 14, 16);
        BaseCoordinate c2 = c.clone();
        Assert.assertEquals(10, c2.width);
        Assert.assertEquals(15, c2.height);
        Assert.assertEquals(14, c2.left);
        Assert.assertEquals(16, c2.top);
    }

    /**
     * Test method for {@link be.yildiz.common.Coordinates#getSize()}.
     */
    @Test
    public void testGetSize() {
        Coordinates c = new Coordinates(10, 15, 14, 16);
        Size s = c.getSize();
        Assert.assertEquals(10, s.width);
        Assert.assertEquals(15, s.height);
    }

}
