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
public class SphereTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            Sphere s = new Sphere(5);
            Assert.assertEquals(5, s.radius, 0.00001f);
        }

        @Test(expected = IllegalArgumentException.class)
        public void zeroRadius() {
            new Sphere(0);
        }


        @Test(expected = IllegalArgumentException.class)
        public void negativeRadius() {
            new Sphere(-1);
        }
    }

    public static class EqualsHashCode {

        @Test
        public void sameHash() {
            Sphere p1 = new Sphere(2);
            Sphere p2 = new Sphere(2);
            Assert.assertEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        public void differentHash() {
            Sphere p1 = new Sphere(2);
            Sphere p2 = new Sphere(3);
            Assert.assertNotEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        public void sameValues() {
            Sphere p1 = new Sphere(2);
            Sphere p2 = new Sphere(2);
            Assert.assertEquals(p1, p2);
        }

        @Test
        public void sameObject() {
            Sphere p1 = new Sphere(2);
            Assert.assertEquals(p1, p1);
        }

        @Test
        public void fromNull() {
            Sphere p1 = new Sphere(2);
            Assert.assertFalse(p1.equals(null));
        }

        @Test
        public void fromOtherType() {
            Sphere p1 = new Sphere(2);
            Assert.assertFalse(p1.equals(2));
        }

        @Test
        public void differentValues() {
            Sphere p1 = new Sphere(2);
            Assert.assertFalse(p1.equals(new Sphere(3)));
        }
    }
}