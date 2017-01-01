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

package be.yildiz.common.util.table;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class TableHeaderTest {

    public static class Constructor {

        @Test
        public void withHappyFlow() {
            TableHeader h = new TableHeader("a", "b", "z");
            Assert.assertEquals(3, h.size());
        }

        @Test(expected = NullPointerException.class)
        public void withOnlyNull() {
            new TableHeader(null);
        }

        @Test(expected = NullPointerException.class)
        public void withNullAndValues() {
            new TableHeader("a", null, "z");
        }
    }

    public static class Size {

        @Test
        public void withZeroElement() {
            TableHeader h = new TableHeader();
            Assert.assertEquals(0, h.size());
        }

        @Test
        public void with3Element() {
            TableHeader h = new TableHeader("f", "z", "z");
            Assert.assertEquals(3, h.size());
        }
    }

    public static class GetColumns {

        @Test
        public void withHappyFlow() {
            TableHeader h = new TableHeader("a", "b", "z");
            Assert.assertEquals(3, h.size());
            Assert.assertEquals("a", h.getColums().get(0));
            Assert.assertEquals("b", h.getColums().get(1));
            Assert.assertEquals("z", h.getColums().get(2));
        }

        @Test(expected = UnsupportedOperationException.class)
        public void andModifyResult() {
            TableHeader h = new TableHeader("a", "b", "z");
            h.getColums().add("b");
        }
    }

}
