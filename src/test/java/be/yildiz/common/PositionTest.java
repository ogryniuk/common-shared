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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public class PositionTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    /**
     * Test method for
     * {@link be.yildiz.common.Position#Position(be.yildiz.common.BaseCoordinate)}
     * .
     */
    @Test
    public void testPositionBaseCoordinate() {
        BaseCoordinate bc = new Position(5, 10);
        Position p = new Position(bc);
        Assert.assertEquals(bc, p);
        this.rule.expect(NullPointerException.class);
        p = new Position(null);
    }

    /**
     * Test method for {@link be.yildiz.common.Position#Position(int)}.
     */
    @Test
    public void testPositionInt() {
        Position p = new Position(10);
        Assert.assertEquals(new Position(10, 10), p);
    }

    /**
     * Test method for {@link be.yildiz.common.Position#Position(int, int)}.
     */
    @Test
    public void testPositionIntInt() {
        Position p = new Position(10, 15);
        Assert.assertEquals(10, p.left);
        Assert.assertEquals(15, p.top);
    }

}
