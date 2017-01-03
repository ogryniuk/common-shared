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

package be.yildiz.common.collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.Map;


/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class BidirectionalMapTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            Assert.assertNotNull(bm);
        }
    }

    public static class GetKey {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Assert.assertEquals("key", bm.getKey("value"));
        }

        @Test
        public void noResult() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            Assert.assertNull(bm.getKey("key"));
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.getKey(null);
        }
    }

    public static class GetKeyOr {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Assert.assertEquals("key", bm.getKeyOr("value", "default"));
        }

        @Test
        public void noResult() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            Assert.assertEquals("default", bm.getKeyOr("key", "default"));
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.getKeyOr(null, "default");
        }

        @Test(expected = NullPointerException.class)
        public void fromDefaultNull() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.getKeyOr("key", null);
        }
    }

    public static class GetValue {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Assert.assertEquals("value", bm.getValue("key"));
        }

        @Test
        public void noResult() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            Assert.assertNull(bm.getValue("key"));
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.getValue(null);
        }
    }

    public static class Put {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Assert.assertEquals("value", bm.getValue("key"));
        }

        @Test(expected = NullPointerException.class)
        public void withNullKey(){
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put(null, "value");
        }

        @Test(expected = NullPointerException.class)
        public void withNullValue(){
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", null);
        }

        @Test
        public void withAlreadyExistingKey(){
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            bm.put("key", "value2");
            Assert.assertEquals("value2", bm.getValue("key"));
        }
    }

    public static class Remove {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Assert.assertEquals("value", bm.getValue("key"));
            Assert.assertEquals("key", bm.getKey("value"));
            bm.remove("key");
            Assert.assertNull(bm.getKey("value"));
            Assert.assertNull(bm.getValue("key"));
        }

        @Test(expected = NullPointerException.class)
        public void withNull() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.remove(null);
        }

        @Test
        public void notExisting() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.remove("key");
        }
    }

    public static class GetIterator {

        @Test
        public void happyFlow() {
            BidirectionalMap<String, String> bm = Maps.newBidirectionalMap();
            bm.put("key", "value");
            Iterator<Map.Entry<String, String>> it = bm.iterator();
            Assert.assertEquals("value", it.next().getValue());
            Assert.assertFalse(it.hasNext());
        }
    }
}
