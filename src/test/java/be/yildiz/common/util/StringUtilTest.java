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

package be.yildiz.common.util;

import be.yildiz.common.id.PlayerId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public final class StringUtilTest {

    public static class ArrayToString {

        @Test
        public void happyFlowObject() {
            Object[] array = {"az", PlayerId.get(2), 5};
            String result = StringUtil.arrayToString(array);
            Assert.assertNotNull(result);
            Assert.assertEquals("az|2|5", result);
        }

        @Test
        public void emptyObject() {
            Object[] array = {};
            String result = StringUtil.arrayToString(array);
            Assert.assertNotNull(result);
            Assert.assertEquals("", result);
        }

        @Test
        public void happyFlowFloats() {
            float[] array = {};
            String result = StringUtil.arrayToString(array);
            Assert.assertNotNull(result);
            Assert.assertEquals("", result);
        }

        @Test
        public void emptyFloats() {
            float[] array = {1f,1.2f,5f};
            String result = StringUtil.arrayToString(array);
            Assert.assertNotNull(result);
            Assert.assertEquals("1.0|1.2|5.0", result);
        }
    }

    public static class BuildRandom {

        @Test
        public void happyFlow() {
            String s = StringUtil.buildRandomString("base");
            String s2 = StringUtil.buildRandomString("base");
            Assert.assertNotEquals(s, s2);
            Assert.assertTrue(s.startsWith("base"));
            Assert.assertTrue(s2.startsWith("base"));
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            StringUtil.buildRandomString(null);
        }
    }

    public static class FillVariable {

        @Test
        public void happyFlow() {
            String base = "hello ${0} !";
            String[] replacement = {"world"};
            Assert.assertEquals("hello world !", StringUtil.fillVariable(base, replacement));
        }

        @Test
        public void empty() {
            String base = "hello ${0} !";
            String[] replacement = {};
            Assert.assertEquals("hello ${0} !", StringUtil.fillVariable(base, replacement));
        }

        @Test
        public void withSeveralBase() {
            String base = "hello ${0} ${0} !";
            String[] replacement = {"world"};
            Assert.assertEquals("hello world world !", StringUtil.fillVariable(base, replacement));
        }

        @Test(expected = NullPointerException.class)
        public void withNullBase() {
            String[] replacement = {"test"};
            StringUtil.fillVariable(null, replacement);
        }

        @Test(expected = NullPointerException.class)
        public void withNullReplacement() {
            StringUtil.fillVariable("base", null);
        }

        @Test
        public void withTooManyReplacement() {
            String base = "hello ${0} !";
            String[] replacement = {"world", "you", "all"};
            Assert.assertEquals("hello world !", StringUtil.fillVariable(base, replacement));
        }

        @Test
        public void withNotEnougReplacement() {
            String base = "hello ${0} ${1} ${2} ${4} !";
            String[] replacement = {"world", "you", "all"};
            Assert.assertEquals("hello world you all ${4} !", StringUtil.fillVariable(base, replacement));
        }

        @Test(expected = NullPointerException.class)
        public void withReplacementContainingNull() {
            String base = "hello ${0} ${1} !";
            String[] replacement = {"world", null};
            StringUtil.fillVariable(base, replacement);
        }

    }

    @Test
    public void testRemoveChar() {
        String s = "abcdef";
        int pos = 2;
        Assert.assertEquals("abdef", StringUtil.removeChar(s, pos));
    }

    @Test
    public void testRemoveLastCharString() {
        String s = "bla14";
        Assert.assertEquals("bla1", StringUtil.removeLastChar(s));
        s = "";
        Assert.assertEquals("", StringUtil.removeLastChar(s));
    }

    @Test
    public void testRemoveLastCharStringBuilder() {
        StringBuilder s = new StringBuilder("bla14");
        Assert.assertEquals("bla1", StringUtil.removeLastChar(s));
        s = new StringBuilder();
        Assert.assertEquals("", StringUtil.removeLastChar(s));
    }

    @Test
    public void testToString() {
        Object[] l = {"test", 5, 'x', new Object()};
        for (Object o : l) {
            Assert.assertEquals(o.toString(), StringUtil.toString(o));
        }
        Assert.assertEquals("null", StringUtil.toString(null));
    }

}
