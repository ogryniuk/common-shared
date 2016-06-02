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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import be.yildiz.common.util.Checker;
import be.yildiz.common.vector.Point2D;

/**
 * Represent a 2D rectangle and contains the 4 extremities data.
 * @author Grégory Van den Borre
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Rectangle {

    /**
     * Left X value.
     */
    private int left;

    /**
     * Up Y value.
     */
    private int top;

    /**
     * Right X value.
     */
    private int right;

    /**
     * Down Y value.
     */
    private int bottom;

    /**
     * Check if a point is contained in this rectangle. i.e. a point with
     * coordinates(5,3) would be contained in a rectangle (x1=0, x2=10, y1=2,
     * y2=4).
     * @param x
     *            X coordinate for the point to check.
     * @param y
     *            Y coordinate for the point to check.
     * @return true if the point is within this rectangle.
     */
    public boolean contain(final int x, final int y) {
        int x1;
        int x2;
        if (this.left <= this.right) {
            x1 = this.left;
            x2 = this.right;
        } else {
            x1 = this.right;
            x2 = this.left;
        }
        int y1;
        int y2;
        if (this.top <= this.bottom) {
            y1 = this.top;
            y2 = this.bottom;
        } else {
            y1 = this.bottom;
            y2 = this.top;
        }
        return Checker.inRange(x, x1, x2) && Checker.inRange(y, y1, y2);
    }

    /**
     * Check if a point is contained in this rectangle. i.e. a point with
     * coordinates(5,3) would be contained in a rectangle (x1=0, x2=10, y1=2,
     * y2=4).
     * @param point
     *            Point to check.
     * @return true if the point is within this rectangle.
     */
    public boolean contain(final Point2D point) {
        return this.contain(point.getX(), point.getY());
    }

    /**
     * @return The rectangle height size.
     */
    public int getHeight() {
        if (this.top > this.bottom) {
            return this.top - this.bottom;
        } else {
            return this.bottom - this.top;
        }
    }

    /**
     * @return The rectangle width size.
     */
    public int getWidth() {
        if (this.left > this.right) {
            return this.left - this.right;
        } else {
            return this.right - this.left;
        }
    }

    /**
     * Update the rectangle coordinates by adding values to it, this behave the
     * same as if the rectangle was moving.
     * @param x
     *            Value to add to left and right.
     * @param y
     *            Value to add to top and bottom.
     */
    public void move(final int x, final int y) {
        this.setLeft(this.left + x);
        this.setRight(this.right + x);
        this.setTop(this.top + y);
        this.setBottom(this.bottom + y);
    }

    /**
     * Check that x1 is smaller than x2 and that y1 is smaller than y2. If not
     * values are swapped.
     */
    public void normalize() {
        int temp;
        if (this.left > this.right) {
            temp = this.left;
            this.left = this.right;
            this.right = temp;
        }
        if (this.top > this.bottom) {
            temp = this.top;
            this.top = this.bottom;
            this.bottom = temp;
        }
    }

    /**
     * Set all values to 0.
     */
    public void reset() {
        this.left = 0;
        this.right = 0;
        this.top = 0;
        this.bottom = 0;
    }

    /**
     * Update the Rectangle values.
     * @param leftValue
     *            New value for left.
     *            New value for top.
     * @param rightValue
     *            New value for right.
     * @param bottomValue
     *            New value for bottom.
     */
    public void setValues(final int leftValue, final int topValue, final int rightValue, final int bottomValue) {
        this.left = leftValue;
        this.right = rightValue;
        this.top = topValue;
        this.bottom = bottomValue;
    }
}
