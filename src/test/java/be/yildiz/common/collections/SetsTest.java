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

package be.yildiz.common.collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class SetsTest {

    public static class NewSet {

        @Test
        public void happyFlow() {
            Set<String> m = Sets.newSet();
            Assert.assertNotNull(m);
            Assert.assertTrue(m.isEmpty());
            Assert.assertTrue(m instanceof HashSet);
        }

        @Test
        public void withValues() {
            Set<String> m = Sets.newSet("azerty", "azerty1");
            Assert.assertNotNull(m);
            Assert.assertEquals(2, m.size());
            Assert.assertTrue(m instanceof HashSet);
        }

        @Test(expected = NullPointerException.class)
        public void withValuesAndNull() {
            Set<String> m = Sets.newSet("azerty", null);
        }

        @Test
        public void fromCollection() {
            List<String> l = Lists.newList("azerty", "azerty1");
            Set<String> m = Sets.newSet(l);
            Assert.assertNotNull(m);
            Assert.assertEquals(2, m.size());
            Assert.assertTrue(m instanceof HashSet);
        }

        @Test(expected = NullPointerException.class)
        public void fromCollectionAndNull() {
            List<String> l = new ArrayList<>();
            l.add("azerty");
            l.add(null);
            Set<String> m = Sets.newSet(l);
        }
    }
}
