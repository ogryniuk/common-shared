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

package be.yildiz.common.vector;

import be.yildiz.common.util.Checker;

import java.security.InvalidParameterException;

/**
 * @author Gregory Van den Borre
 */
public interface VectorUtil {

    /**
     * Compute position for point on a circle.
     *
     * @param number Number of points to set on the circle.
     * @param radius Circle ray size.
     * @return An array (with size of parameter number) containing the position
     * of every points, assuming the circle is at 0,0.
     * @throws InvalidParameterException if number is <= 0.
     * @throws InvalidParameterException if radius is <= 0.
     */
    static Point2D[] getPointOnCircle(final int number, final int radius) {
        Checker.exceptionNotGreaterThanZero(number);
        Checker.exceptionNotGreaterThanZero(radius);
        double angle = 2 * Math.PI / number;
        double value = 0;
        Point2D[] result = new Point2D[number];
        for (int i = 0; i < number; i++) {
            value += angle;
            double x = radius * Math.cos(value);
            double y = radius * Math.sin(value);
            result[i] = new Point2D(x, y);
        }
        return result;
    }

}
