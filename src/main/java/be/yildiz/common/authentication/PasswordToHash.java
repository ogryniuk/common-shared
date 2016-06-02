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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import be.yildiz.common.resource.ResourceUtil;

/**
 * Wrap a password, take a clear password as input and hash it. Once this object
 * is built, the password in clear is lost.
 * @author Grégory Van den Borre
 */
public final class PasswordToHash extends Password {

    /**
     * Add a salt to a password.
     * @param clearPassword
     *            Clear password.
     * @return The salted password.
     */
    private static String salt(final String clearPassword) {
        //FIXME <MEDIUM> implements.
        return clearPassword;
    }

    /**
     * Hash the password.
     * @param clearPassword
     *            Clear password.
     * @return the hashed password.
     */
    private static String hash(final String clearPassword) {
        //FIXME <HIGH> use a better hash algo
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(ResourceUtil.getByteArray(clearPassword));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("MD5 algorithm is not available.");
        }
        final StringBuilder hashString = new StringBuilder();
        for (byte b : hash) {
            final String hex = Integer.toHexString(b);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }

    /**
     * Build a hashed password from a clear password.
     * @param clearPassword
     *            Clear password.
     */
    public PasswordToHash(final String clearPassword) {
        super(PasswordToHash.hash(PasswordToHash.salt(clearPassword)));
    }
}
