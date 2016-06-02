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

import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

/**
 * List of rules for the authentication.
 * @author Grégory Van den Borre
 */
@AllArgsConstructor
public final class AuthenticationRules {

    /**
     * Default authentication, login size between 3 and 20, password size
     * between 8 and 20, no special characters allowed for both.
     */
    public static final AuthenticationRules DEFAULT = new AuthenticationRules(20, 20, 3, 5, Pattern.compile("[a-zA-Z0-9]*"),
            Pattern.compile("[a-zA-Z0-9]*"));

    /**
     * Login maximum length.
     */
    public final int loginMaxLength;

    /**
     * Password maximum length.
     */
    public final int passMaxLength;

    /**
     * Login minimum length.
     */
    public final int loginMinLength;

    /**
     * Password minimum length.
     */
    public final int passMinLength;

    /**
     * Login accepted characters.
     */
    public final Pattern loginPattern;

    /**
     * Password accepted characters.
     */
    public final Pattern passPattern;

}
