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

package be.yildiz.common.exeption;

import be.yildiz.common.log.Logger;

/**
 * High level exception meant to be sent to the user, this is the exception
 * dealing with all technical issues occuring during the application runtime. It
 * will log the cause exception.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class TechnicalException extends RuntimeException {

    /***/
    private static final long serialVersionUID = -4453166840479422489L;

    /**
     * Message to display to the user(most probably the key for translation).
     */
    public final String message;

    public TechnicalException(final Exception cause, final String message) {
        super(cause);
        Logger.error(cause);
        this.message = message;
    }

    /**
     * Create a new instance with a cause and an empty message.
     *
     * @param cause Root exception.
     */
    //@Requires("cause != null")
    public TechnicalException(final Exception cause) {
        this(cause, "");
    }

}
