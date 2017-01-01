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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public final class StringUtilTest {

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
        Object[] l = {"test", new Integer(5), 'x', new Object()};
        for (Object o : l) {
            Assert.assertEquals(o.toString(), StringUtil.toString(o));
        }
        Assert.assertEquals("null", StringUtil.toString(null));
    }

}
