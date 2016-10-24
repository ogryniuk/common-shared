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

import java.util.regex.Pattern;

public class AuthenticationRuleTest {

    @Test
    public void testAuthenticationRulesDefault() {
        Assert.assertEquals(20, AuthenticationRules.DEFAULT.loginMaxLength);
        Assert.assertEquals(20, AuthenticationRules.DEFAULT.passMaxLength);
        Assert.assertEquals(5, AuthenticationRules.DEFAULT.passMinLength);
        Assert.assertEquals(3, AuthenticationRules.DEFAULT.loginMinLength);
        Assert.assertEquals("[a-zA-Z0-9]*", AuthenticationRules.DEFAULT.loginPattern.pattern());
        Assert.assertEquals("[a-zA-Z0-9]*", AuthenticationRules.DEFAULT.passPattern.pattern());
    }

    @Test
    public void testAuthenticationRules() {
        AuthenticationRules r = new AuthenticationRules(10, 15, 3, 5, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
        Assert.assertEquals(10, r.loginMaxLength);
        Assert.assertEquals(15, r.passMaxLength);
        Assert.assertEquals(5, r.passMinLength);
        Assert.assertEquals(3, r.loginMinLength);
        Assert.assertEquals("[0-9]*", r.loginPattern.pattern());
        Assert.assertEquals("[a-z]*", r.passPattern.pattern());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginMaxSmallerZero() {
        new AuthenticationRules(-1, 15, 3, 5, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginMinSmallerZero() {
        new AuthenticationRules(10, 15, -1, 5, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPassMinSmallerZero() {
        new AuthenticationRules(10, 15, 8, -1, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginMaxSmallerMin() {
        new AuthenticationRules(2, 15, 3, 5, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPassMaxSmallerMin() {
        new AuthenticationRules(10, 2, 3, 5, Pattern.compile("[0-9]*"), Pattern.compile("[a-z]*"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullLoginPattern() {
        new AuthenticationRules(10, 15, 3, 5, null, Pattern.compile("[a-z]*"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullPassPattern() {
        new AuthenticationRules(10, 15, 3, 5, Pattern.compile("[a-z]*"), null);
    }
}
