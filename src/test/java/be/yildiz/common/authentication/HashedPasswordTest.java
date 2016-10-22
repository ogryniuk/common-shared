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

package be.yildiz.common.authentication;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public final class HashedPasswordTest {

    @Test
    public void testHashedPassword() {
        Assert.assertEquals(AuthenticationTestHelper.PASSWORD_OK, AuthenticationTestHelper.HASHED_PASSWORD_OK.getHashedPassword());
    }

    @Test(expected = NullPointerException.class)
    public void testHashedPasswordNull() {
        new HashedPassword(null);
    }

    @Test
    public void testHashedPasswordHashCodeEquals() {
        Password p = new HashedPassword(AuthenticationTestHelper.MD5_ENCODED);
        Password p1 = new PasswordToHash(AuthenticationTestHelper.PASSWORD_OK);
        Assert.assertEquals(p.hashCode(), p1.hashCode());
        Assert.assertEquals(p, p);
        Assert.assertEquals(p1, p1);
        Assert.assertEquals(p, p1);
        Assert.assertNotEquals(p, null);
        Assert.assertNotEquals(p, AuthenticationTestHelper.MD5_ENCODED);
        Assert.assertNotEquals(p, new HashedPassword(AuthenticationTestHelper.MD5_ENCODED + "a"));
    }

}
