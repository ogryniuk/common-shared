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

package be.yildiz.common;

import lombok.EqualsAndHashCode;
import be.yildiz.common.util.Checker;

/**
 * Base class for coordinates, only to be subclassed.
 * @author Grégory Van den Borre
 */
@EqualsAndHashCode
public class BaseCoordinate {

    /**
     * Constant with 0 values coordinates.
     */
    public static final BaseCoordinate ZERO = new BaseCoordinate(0, 0, 0, 0);

    /**
     * Width value.
     */
    public final int width;

    /**
     * Height value.
     */
    public final int height;

    /**
     * X position value.
     */
    public final int left;

    /**
     * Y position value.
     */
    public final int top;

    /**
     * Full constructor.
     * @param widthValue
     *            Width coordinate, must be positive.
     * @param heightValue
     *            Height coordinate, must be positive.
     * @param leftValue
     *            Left coordinate.
     * @param topValue
     *            Top coordinate.
     */
    protected BaseCoordinate(final int widthValue, final int heightValue, final int leftValue, final int topValue) {
        super();
        Checker.exceptionNotPositive(widthValue);
        Checker.exceptionNotPositive(heightValue);
        this.width = widthValue;
        this.height = heightValue;
        this.left = leftValue;
        this.top = topValue;
    }

    /**
     * Check if a coordinate is contained in another one.
     * @param toCheck
     *            Point to check.
     * @return <code>true</code> if the given coordinate is contained
     */
    public final boolean contains(final BaseCoordinate toCheck) {
        return toCheck.left >= this.left && toCheck.left + toCheck.width <= this.left + this.width && toCheck.top >= this.top
                && toCheck.top + toCheck.height <= this.top + this.height;
    }

    /**
     * @return The coordinates values.
     */
    @Override
    public final String toString() {
        return "Width:" + this.width + ", height:" + this.height + ", left:" + this.left + ", top:" + this.top;
    }
}
