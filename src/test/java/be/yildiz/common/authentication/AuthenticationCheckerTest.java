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
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static be.yildiz.common.authentication.AuthenticationTestHelper.*;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public final class AuthenticationCheckerTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
        }

        @Test(expected = NullPointerException.class)
        public void withNull() {
            new AuthenticationChecker(null);
        }
    }

    public static class CheckStringString {

        @Test
        public void happyFlow() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            c.check(LOGIN_OK, PASSWORD_OK);
        }

        @Test(expected = NullPointerException.class)
        public void stringNull() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            c.check(null, PASSWORD_OK);
        }

        @Test
        public void loginTooShort() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_TOO_SHORT, PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.LOGIN_TOO_SHORT);
            }
        }

        @Test
        public void loginTooLong() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_TOO_LONG, PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.LOGIN_TOO_LONG);
            }
        }

        @Test
        public void passwordTooLong() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_OK, PASSWORD_TOO_LONG);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.PASS_TOO_LONG);
            }
        }

        @Test
        public void passwordTooShort() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_OK, PASSWORD_TOO_SHORT);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.PASS_TOO_SHORT);
            }
        }

        @Test
        public void loginInvalid() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_INVALID, PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.INVALID_LOGIN_CHAR);
            }
        }

        @Test
        public void passwordInvalid() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_OK, PASSWORD_INVALID);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.INVALID_PASS_CHAR);
            }
        }
    }

    public static class CheckStringPassword {

        @Test
        public void happyFlow() throws CredentialException{
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            c.check(LOGIN_OK, HASHED_PASSWORD_OK);
        }

        @Test(expected = NullPointerException.class)
        public void stringNull() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            c.check(null, HASHED_PASSWORD_OK);
        }

        @Test
        public void loginTooShort() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_TOO_SHORT, HASHED_PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.LOGIN_TOO_SHORT);
            }
        }

        @Test
        public void loginTooLong() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_TOO_LONG, HASHED_PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.LOGIN_TOO_LONG);
            }
        }

        @Test
        public void passwordTooLong() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            // Hashed size is not computed.
            c.check(LOGIN_OK, HASHED_PASSWORD_TOO_LONG);
        }

        @Test
        public void passwordTooShort() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            // Hashed size is not computed.
            c.check(LOGIN_OK, HASHED_PASSWORD_TOO_SHORT);
        }

        @Test
        public void loginInvalid() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            try {
                c.check(LOGIN_INVALID, PASSWORD_OK);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.INVALID_LOGIN_CHAR);
            }
        }

        @Test
        public void passwordInvalid() {
            AuthenticationChecker c = new AuthenticationChecker(AuthenticationRules.DEFAULT);
            try {
                c.check(LOGIN_OK, PASSWORD_INVALID);
                Assert.fail("Should have thrown exception");
            } catch (CredentialException e) {
                checkCredentialError(e, AuthenticationError.INVALID_PASS_CHAR);
            }
        }
    }

    private static void checkCredentialError(CredentialException e, AuthenticationError expected) {
        Assert.assertEquals(1, e.getErrors().size());
        Assert.assertEquals(expected, e.getErrors().get(0));
    }

}
