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
public class BoxTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            Box b = new Box(1,2,3);
            Assert.assertEquals(1, b.width);
            Assert.assertEquals(2, b.height);
            Assert.assertEquals(3, b.depth);
        }

        @Test
        public void happyFlow1Arg() {
            Box b = new Box(5);
            Assert.assertEquals(5, b.width);
            Assert.assertEquals(5, b.height);
            Assert.assertEquals(5, b.depth);
        }

        @Test(expected = IllegalArgumentException.class)
        public void zeroWidth() {
            new Box(0,2,3);
        }

        @Test(expected = IllegalArgumentException.class)
        public void zeroHeight() {
            new Box(1,0,3);
        }

        @Test(expected = IllegalArgumentException.class)
        public void zeroDepth() {
            new Box(1,2,0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeWidth() {
            new Box(-1,2,3);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeHeight() {
            new Box(1,-1,3);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeDepth() {
            new Box(1,2,-1);
        }
    }

    public static class EqualsHashCode {

        @Test
        public void sameHash() {
            Box b1 = new Box(2);
            Box b2 = new Box(2);
            Assert.assertEquals(b1.hashCode(), b2.hashCode());
        }

        @Test
        public void differentHash() {
            Box b1 = new Box(2);
            Box b2 = new Box(3);
            Assert.assertNotEquals(b1.hashCode(), b2.hashCode());
        }

        @Test
        public void sameValues() {
            Box b1 = new Box(2);
            Box b2 = new Box(2);
            Assert.assertEquals(b1, b2);
        }

        @Test
        public void sameObject() {
            Box b1 = new Box(2);
            Assert.assertEquals(b1, b1);
        }

        @Test
        public void fromNull() {
            Box b1 = new Box(2);
            Assert.assertFalse(b1.equals(null));
        }

        @Test
        public void fromOtherType() {
            Box b1 = new Box(2);
            Assert.assertFalse(b1.equals(2));
        }

        @Test
        public void differentValues() {
            Box b1 = new Box(2);
            Assert.assertFalse(b1.equals(new Box(3)));
        }
    }
}