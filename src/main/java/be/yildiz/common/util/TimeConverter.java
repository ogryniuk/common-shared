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

package be.yildiz.common.util;

import lombok.Getter;

/**
 * Convert millisec in sec min hour.
 *
 * @author Grégory Van den Borre
 */
public final class TimeConverter {

    /**
     * Number of second from a millisecond value.
     */
    private static final float MS_TO_SEC = 0.001f;

    /**
     * Number of seconds in one minute.
     */
    private static final int SEC_IN_MIN = 60;

    /**
     * Computed number of seconds.
     */
    @Getter
    private int sec;

    /**
     * Computed number of minutes.
     */
    @Getter
    private int min;

    /**
     * Simple constructor.
     */
    public TimeConverter() {
        super();
    }

    /**
     * Set the time in millisecond to be converted in a number of seconds,
     * minutes, hours.
     *
     * @param time Time to use.
     */
    public void setTime(final long time) {
        int total = Float.valueOf(time * TimeConverter.MS_TO_SEC).intValue();
        this.sec = total % TimeConverter.SEC_IN_MIN;
        this.min = Float.valueOf((total - this.sec) / (float) TimeConverter.SEC_IN_MIN).intValue();
    }
}
