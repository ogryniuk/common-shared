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

package be.yildiz.common.shape;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class PlaneTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            Plane p = new Plane(1,2);
            Assert.assertEquals(1, p.width);
            Assert.assertEquals(2, p.depth);
        }

        @Test
        public void happyFlow1Arg() {
            Plane p = new Plane(1);
            Assert.assertEquals(1, p.width);
            Assert.assertEquals(1, p.depth);
        }

        @Test(expected = IllegalArgumentException.class)
        public void zeroWidth() {
            new Plane(0,1);
        }


        @Test(expected = IllegalArgumentException.class)
        public void zeroDepth() {
            new Plane(1,0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeWidth() {
            new Plane(-1,3);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeDepth() {
            new Plane(1,-1);
        }
    }

    public static class EqualsHashCode {

        @Test
        public void sameHash() {
            Plane p1 = new Plane(2);
            Plane p2 = new Plane(2);
            Assert.assertEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        public void differentHash() {
            Plane p1 = new Plane(2);
            Plane p2 = new Plane(3);
            Assert.assertNotEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        public void sameValues() {
            Plane p1 = new Plane(2);
            Plane p2 = new Plane(2);
            Assert.assertEquals(p1, p2);
        }

        @Test
        public void sameObject() {
            Plane p1 = new Plane(2);
            Assert.assertEquals(p1, p1);
        }

        @Test
        public void fromNull() {
            Plane p1 = new Plane(2);
            Assert.assertFalse(p1.equals(null));
        }

        @Test
        public void fromOtherType() {
            Plane p1 = new Plane(2);
            Assert.assertFalse(p1.equals(2));
        }

        @Test
        public void differentValues() {
            Plane p1 = new Plane(2);
            Assert.assertFalse(p1.equals(new Plane(3)));
        }
    }
}