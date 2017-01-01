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

package be.yildiz.common;

import be.yildiz.common.id.PlayerId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class TokenTest {

    public static class Authenticated {

        @Test
        public void happyFlow() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Assert.assertEquals(PlayerId.get(6), t.getId());
            Assert.assertEquals(12, t.getAuthenticationTime());
            Assert.assertEquals(10, t.getKey());
            Assert.assertEquals(Token.Status.AUTHENTICATED, t.getStatus());
            Assert.assertTrue(t.isAuthenticated());
        }

        @Test(expected = NullPointerException.class)
        public void withNullPlayer() {
            Token.authenticated(null, 0, 10);
        }

        @Test(expected = IllegalArgumentException.class)
        public void withNegativeTime() {
            Token.authenticated(PlayerId.get(6), -1, 10);
        }
    }

    public static class AuthenticationFailed {

        @Test
        public void happyFlow() {
            Token t = Token.authenticationFailed();
            Assert.assertEquals(PlayerId.WORLD, t.getId());
            Assert.assertEquals(0, t.getAuthenticationTime());
            Assert.assertEquals(-1, t.getKey());
            Assert.assertEquals(Token.Status.NOT_AUTHENTICATED, t.getStatus());
            Assert.assertFalse(t.isAuthenticated());
        }
    }

    public static class Banned {

        @Test
        public void happyFlow() {
            Token t = Token.banned();
            Assert.assertEquals(PlayerId.WORLD, t.getId());
            Assert.assertEquals(0, t.getAuthenticationTime());
            Assert.assertEquals(-1, t.getKey());
            Assert.assertEquals(Token.Status.BANNED, t.getStatus());
            Assert.assertFalse(t.isAuthenticated());
        }
    }


    public static class NotFound {

        @Test
        public void happyFlow() {
            Token t = Token.notFound();
            Assert.assertEquals(PlayerId.WORLD, t.getId());
            Assert.assertEquals(0, t.getAuthenticationTime());
            Assert.assertEquals(-1, t.getKey());
            Assert.assertEquals(Token.Status.NOT_FOUND, t.getStatus());
            Assert.assertFalse(t.isAuthenticated());
        }
    }


    public static class Any {

        @Test
        public void happyFlow() {
            Token t = Token.any(PlayerId.get(6), 10, Token.Status.NOT_AUTHENTICATED);
            Assert.assertEquals(PlayerId.get(6), t.getId());
            Assert.assertEquals(0, t.getAuthenticationTime());
            Assert.assertEquals(10, t.getKey());
            Assert.assertEquals(Token.Status.NOT_AUTHENTICATED, t.getStatus());
        }

        @Test(expected = NullPointerException.class)
        public void withNullPlayer() {
            Token.any(null, 10, Token.Status.AUTHENTICATED);
        }

        @Test(expected = NullPointerException.class)
        public void withNullStatus() {
            Token.any(PlayerId.get(6), 10, null);
        }

    }

    public static class HashCode {

        @Test
        public void isSame() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t3 = Token.authenticated(PlayerId.get(6), 25, 10);
            Assert.assertEquals(t.hashCode(), t2.hashCode());
            Assert.assertEquals(t.hashCode(), t3.hashCode());
        }

        @Test
        public void isNotSame() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(7), 12, 10);
            Token t3 = Token.authenticated(PlayerId.get(6), 12, 12);
            Assert.assertNotEquals(t.hashCode(), t2.hashCode());
            Assert.assertNotEquals(t.hashCode(), t3.hashCode());
        }
    }

    public static class Equals {

        @Test
        public void isSameObject() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Assert.assertTrue(t.equals(t));
        }

        @Test
        public void isSame() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(6), 12, 10);
            Assert.assertTrue(t.equals(t2));
        }

        @Test
        public void withDifferentPlayer() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(7), 12, 10);
            Assert.assertFalse(t.equals(t2));
        }

        @Test
        public void withDifferentTime() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(6), 25, 10);
            Assert.assertTrue(t.equals(t2));
        }

        @Test
        public void withDifferentKey() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.get(6), 12, 11);
            Assert.assertFalse(t.equals(t2));
        }

        @Test
        public void withNull() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Assert.assertFalse(t.equals(null));
        }

        @Test
        public void withOtherType() {
            Token t = Token.authenticated(PlayerId.get(6), 12, 10);
            Assert.assertFalse(t.equals("ok"));
        }

        @Test
        public void withDifferentStatus() {
            Token t = Token.any(PlayerId.get(6), 10, Token.Status.AUTHENTICATED);
            Token t2 = Token.any(PlayerId.get(6), 10, Token.Status.NOT_AUTHENTICATED);
            Assert.assertFalse(t.equals(t2));
        }
    }


}