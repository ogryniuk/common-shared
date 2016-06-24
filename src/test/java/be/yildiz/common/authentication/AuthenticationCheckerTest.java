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

import be.yildiz.common.authentication.AuthenticationChecker.AuthenticationError;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public final class AuthenticationCheckerTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, be.yildiz.common.authentication.Password)}
     * .
     */
    @Test
    public void testCheckStringPassword() {
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, java.lang.String)}
     * .
     *
     * @throws CredentialException
     */
    @Test
    public void testCheckStringString() throws CredentialException {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        c.check("test", "abcde");
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, java.lang.String)}
     * .
     *
     * @throws CredentialException
     */
    @Test
    public void testCheckStringStringNull() throws CredentialException {
        this.rule.expect(NullPointerException.class);
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        c.check(null, "abcde");
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, java.lang.String)}
     * .
     *
     * @throws CredentialException
     */
    @Test
    public void testCheckStringStringLoginTooShort() {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            c.check("", "abcde");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(AuthenticationError.LOGIN_TOO_SHORT, e.getErrors().get(0));
        }
        try {
            c.check("", new HashedPassword("abcde"));
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(AuthenticationError.LOGIN_TOO_SHORT, e.getErrors().get(0));
        }
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, java.lang.String)}
     * .
     *
     * @throws CredentialException
     */
    @Test
    public void testCheckStringStringLoginTooLong() throws CredentialException {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            c.check("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "abcde");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.LOGIN_TOO_LONG, e.getErrors().get(0));
        }
        try {
            c.check("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new HashedPassword("abcde"));
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.LOGIN_TOO_LONG, e.getErrors().get(0));
        }
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#check(java.lang.String, java.lang.String)}
     * .
     *
     * @throws CredentialException
     */
    @Test
    public void testCheckStringStringPasswordTooLong() throws CredentialException {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            c.check("aaaa", "abcdeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.PASS_TOO_LONG, e.getErrors().get(0));
        }
        // Hashed size is not computed.
        c.check("aaaa", new HashedPassword("abcdeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void testCheckStringStringPasswordTooShort() throws CredentialException {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            c.check("aaaa", "abc");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.PASS_TOO_SHORT, e.getErrors().get(0));
        }
        // Hashed size is not computed.
        c.check("aaaa", new HashedPassword("a"));
    }

    @Test
    public void testCheckLoginMatcher() {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            c.check("&&&&&", "aaaaa");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.INVALID_LOGIN_CHAR, e.getErrors().get(0));
        }
    }

    @Test
    public void testCheckPasswordMatcher() {
        AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
        try {
            // FIXME check every invalid char
            c.check("aaaaa", "&&&&&&");
            Assert.fail();
        } catch (CredentialException e) {
            Assert.assertEquals(1, e.getErrors().size());
            Assert.assertEquals(AuthenticationError.INVALID_PASS_CHAR, e.getErrors().get(0));
        }
    }

    /**
     * Test method for
     * {@link be.yildiz.common.authentication.AuthenticationChecker#AuthenticationChecker(AuthenticationRules)}
     * .
     */
    @Test
    public void testAuthenticationCheckerNull() {
        this.rule.expect(NullPointerException.class);
        new AuthenticationChecker(null);
    }

}
