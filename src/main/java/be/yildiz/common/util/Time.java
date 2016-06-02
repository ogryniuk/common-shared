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

import lombok.EqualsAndHashCode;

/**
 * Class to represent a time period, must be sub classed to create different types of periods.
 * 
 * @immutable
 * 
 * @author Grégory Van den Borre
 */
@EqualsAndHashCode
public final class Time {

    public static final Time ZERO = new Time(0);
    /**
     * Time period in milliseconds.
     */
    public final long timeInMs;

    /**
     * Full constructor, only to be called from children.
     * 
     * @ensures time <= Long.MAX_VALUE
     * 
     * @param time
     *            Time in milliseconds.
     * @throws IllegalArgumentException
     *             if the time is negative.
     */
    private Time(final long time) {
        super();
        Checker.exceptionNotPositive(time);
        this.timeInMs = time;
    }

    /**
     * Create a new Time from milliseconds value.
     * 
     * @ensures ms <= Long.MAX_VALUE
     * 
     * @param ms
     *            Time value.
     * @return The created time.
     * @throws IllegalArgumentException
     *             if the time is negative.
     */
    public static Time milliSeconds(final long ms) {
        return new Time(ms);
    }

    /**
     * Create a new Time from seconds value.
     * 
     * @ensures second <= (Long.MAX_VALUE / 1000)
     * 
     * @param second
     *            Time value.
     * @return The created time.
     * @throws IllegalArgumentException
     *             if the time is negative.
     */
    public static Time seconds(final long second) {
        return milliSeconds(second * 1000);
    }

    public static Time minutes(final int minute) {
        return seconds(minute * 60);
    }

    public static Time hours(final int hour) {
        return minutes(hour * 60);
    }

    /**
     * Create a new Time with the current time value + this object time.
     * 
     * @return The created time.
     */
    public Time addNow() {
        return Time.milliSeconds(System.currentTimeMillis() + this.timeInMs);
    }

    /**
     * Subtract a time millisecond value from this millisecond value.
     * 
     * @param value
     *            Value to subtract.
     * @return This time - the given time.
     */
    public long subtractMs(final long value) {
        return this.timeInMs - value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.timeInMs);
    }

    /**
     * @return <code>true</code> if this time is greater than the current time.
     */
    public boolean isNotElapsed() {
        return this.timeInMs > System.currentTimeMillis();
    }
    
    public Time subtract(final Time other) {
        return new Time(this.timeInMs - other.timeInMs);
    }
}
