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

package be.yildiz.common.id;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class ActionIdTest {

    public static class Get {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.get(1000);
            Assert.assertNotNull(id);
            Assert.assertEquals(1000, id.value);
        }

        @Test
        public void cached() {
            ActionId id = ActionId.get(2000);
            ActionId id2 = ActionId.get(2000);
            Assert.assertTrue(id == id2);
        }
    }

    public static class IsWorld {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.WORLD;
            Assert.assertTrue(id.isWorld());
        }

        @Test
        public void not() {
            ActionId id = ActionId.get(5);
            Assert.assertFalse(id.isWorld());
        }

        @Test
        public void fromId() {
            Assert.assertTrue(ActionId.isWorld(ActionId.WORLD));
        }

        @Test
        public void fromInt() {
            Assert.assertTrue(ActionId.isWorld(ActionId.WORLD.value));
        }

        @Test
        public void fromIdNot() {
            Assert.assertFalse(ActionId.isWorld(ActionId.get(2)));
        }

        @Test(expected = IllegalArgumentException.class)
        public void fromIdNull() {
            ActionId.isWorld(null);
        }

        @Test
        public void fromIntNot() {
            Assert.assertFalse(ActionId.isWorld(4));
        }
    }

    public static class IsNegative {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.get(-1);
            Assert.assertTrue(id.isNegative());
        }

        @Test
        public void not() {
            ActionId id = ActionId.get(5);
            Assert.assertFalse(id.isNegative());
        }
    }

    public static class HashCode {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.get(5);
            ActionId id2 = ActionId.get(5);
            Assert.assertEquals(id.hashCode(), id2.hashCode());
        }
    }

    public static class Equals {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.get(5);
            ActionId id2 = ActionId.get(5);
            Assert.assertEquals(id, id2);
        }

        @Test
        public void notSame() {
            ActionId id = ActionId.get(5);
            ActionId id2 = ActionId.get(6);
            Assert.assertNotEquals(id, id2);
        }

        @Test(expected = IllegalArgumentException.class)
        public void otherType() {
            ActionId id = ActionId.get(5);
            id.equals("ok");
        }
    }

    public static class ToString {

        @Test
        public void happyFlow() {
            ActionId id = ActionId.get(8);
            Assert.assertEquals("8", id.toString());
        }
    }
}
