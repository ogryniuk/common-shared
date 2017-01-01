/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.common.vector;

import be.yildiz.common.log.Logger;
import be.yildiz.common.util.Literals;

import java.security.InvalidParameterException;

/**
 * Simple class to hold 3d coordinates, immutable.
 *
 * @author Grégory Van den Borre
 */
public final class Point3D {

    /**
     * Initial direction for all built object, axis is -Z.
     */
    public static final Point3D BASE_DIRECTION = new Point3D(0, 0, -1);

    /**
     * Constant with Y = -1.
     */
    public static final Point3D INVERT_Y = new Point3D(0, -1, 0);

    /**
     * Constant with X = 1.
     */
    public static final Point3D X = new Point3D(1, 0, 0);

    /**
     * Constant with Y = 1.
     */
    public static final Point3D Y = new Point3D(0, 1, 0);

    /**
     * Constant with Z = 1.
     */
    public static final Point3D Z = new Point3D(0, 0, 1);

    /**
     * Constant with Z = -1.
     */
    public static final Point3D INVERT_Z = new Point3D(0, 0, -1);

    /**
     * Constant with all values as 0.
     */
    public static final Point3D ZERO = new Point3D(0, 0, 0);

    public static final Point3D INVERT_X = new Point3D(-1, 0, 0);

    /**
     * Value for the x axis.
     */
    public final float x;

    /**
     * Value for the y axis.
     */
    public final float y;

    /**
     * Value for the z axis.
     */
    public final float z;

    /**
     * Full constructor.
     *
     * @param values Value to set to X, Y and Z.
     */
    public Point3D(final float values) {
        this(values, values, values);
    }

    /**
     * Full constructor.
     *
     * @param xValue Value for the x axis.
     * @param yValue Value for the y axis.
     * @param zValue Value for the z axis.
     */
    private Point3D(final float xValue, final float yValue, final float zValue) {
        super();
        this.x = xValue;
        this.y = yValue;
        this.z = zValue;
    }

    /**
     * Full constructor, parse a String to build the point. Throws InvalidParameterException if parsing fails.
     *
     * @param toParse String to parse.
     */
    public Point3D(final String toParse) {
        try {
            String[] values = toParse.split(Literals.VECTOR_SEPARATOR);
            if (values.length == 3) {
                this.x = Float.parseFloat(values[0]);
                this.y = Float.parseFloat(values[1]);
                this.z = Float.parseFloat(values[2]);
            } else if (values.length == 2) {
                this.x = Float.parseFloat(values[0]);
                this.y = 0;
                this.z = Float.parseFloat(values[1]);
            } else {
                throw new InvalidParameterException("Invalid value while parsing String:" + toParse);
            }
        } catch (NumberFormatException nfe) {
            Logger.error(nfe);
            throw new InvalidParameterException("Invalid value while parsing String:" + toParse);
        }
    }

    public static Point3D xyz(final float x, final float y, final float z) {
        return new Point3D(x, y, z);
    }

    public static Point3D xyz(final float[] array) {
        return xyz(array[0], array[1], array[2]);
    }

    public static Point3D xyz(final float xyz) {
        return new Point3D(xyz, xyz, xyz);
    }

    public static Point3D x(final float x) {
        return new Point3D(x, 0, 0);
    }

    public static Point3D z(final float z) {
        return new Point3D(0, 0, z);
    }

    public static Point3D xz(final float x, final float z) {
        return xyz(x, 0, z);
    }

    public static Point3D xy(final float x, final float y) {
        return new Point3D(x, y, 0);
    }

    public static Point3D xz(final float xz) {
        return xz(xz, xz);
    }

    public static Point3D yz(final float y, final float z) {
        return new Point3D(0, y, z);
    }

    public static Point3D y(final float y) {
        return new Point3D(0, y, 0);
    }

    /**
     * Create a new point from the addition of other points.
     *
     * @param points Point3D to add to compute the result.
     * @return A Point3D resulting from the addition of the given points.
     */
    public static Point3D addPoints(final Point3D... points) {
        float x = 0;
        float y = 0;
        float z = 0;
        for (Point3D point : points) {
            x += point.x;
            y += point.y;
            z += point.z;
        }
        return new Point3D(x, y, z);
    }

    /**
     * Normalize a point.
     *
     * @param p Point to normalize.
     * @return A new normalized point.
     */
    public static Point3D normalize(final Point3D p) {
        return Point3D.normalizeAndMultiply(p, 1);
    }

    /**
     * Normalize a point and multiply the values.
     *
     * @param p      Point to normalize.
     * @param scalar Value to multiply.
     * @return A new point normalized and multiplied.
     */
    public static Point3D normalizeAndMultiply(final Point3D p, final float scalar) {
        float length = (float) Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
        if (Math.abs(length) > 0.000001f){
            return new Point3D(scalar * (p.x / length), scalar * (p.y / length), scalar * (p.z / length));
        }
        return p;
    }

    /**
     * Compute squared distance between 2 points.
     *
     * @param p1 First point.
     * @param p2 Second point.
     * @return The squared distance between the 2 points.
     */
    public static float squaredDistance(final Point3D p1, final Point3D p2) {
        final float valueX = p1.x - p2.x;
        final float valueY = p1.y - p2.y;
        final float valueZ = p1.z - p2.z;
        return valueX * valueX + valueY * valueY + valueZ * valueZ;
    }

    /**
     * Add values to the values of this point.
     *
     * @param x Value to add to this point X value.
     * @param y Value to add to this point Y value.
     * @param z Value to add to this point Z value.
     * @return A new point with added values.
     */
    public Point3D add(final float x, final float y, final float z) {
        return new Point3D(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Add a point to this one.
     *
     * @param point Point to add.
     * @return A new point with added values.
     */
    public Point3D add(final Point3D point) {
        return new Point3D(this.x + point.x, this.y + point.y, this.z + point.z);
    }

    /**
     * Add a value to the X value of this point.
     *
     * @param value Value to add to this point X value.
     * @return A new point with added value.
     */
    public Point3D addX(final float value) {
        return new Point3D(this.x + value, this.y, this.z);
    }

    /**
     * Subtract a value to the X value of this point.
     *
     * @param value Value to subtract to this point X value.
     * @return A new point with subtracted value.
     */
    public Point3D subtractX(final float value) {
        return new Point3D(this.x - value, this.y, this.z);
    }

    /**
     * Add a value to the Y value of this point.
     *
     * @param value value to add to this point Y value.
     * @return A new point with added value.
     */
    public Point3D addY(final float value) {
        return new Point3D(this.x, this.y + value, this.z);
    }

    /**
     * Add a value to the Z value of this point.
     *
     * @param value value to add to this point Z value.
     * @return A new point with added value.
     */
    public Point3D addZ(final float value) {
        return new Point3D(this.x, this.y, this.z + value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point3D)) {
            return false;
        }
        Point3D other = (Point3D) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return Float.floatToIntBits(this.z) == Float.floatToIntBits(other.z);
    }

    /**
     * Get this point angle.
     *
     * @param axis Axis to use.
     * @return The point angle.
     */
    public float getAngle(final Axis axis) {
        switch (axis) {
            case XY:
                return (float) Math.atan2(this.y, this.x);
            case XZ:
                return (float) Math.atan2(this.z, this.x);
            case ZY:
                return (float) Math.atan2(this.z, this.y);
            default:
                throw new InvalidParameterException("value not implemented.");
        }
    }

    /**
     * @return This point with all values inverted(5,10,-8 will be -5,-10,8).
     */
    public Point3D getInverse() {
        return new Point3D(-this.x, -this.y, -this.z);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(this.x);
        result = prime * result + Float.floatToIntBits(this.y);
        result = prime * result + Float.floatToIntBits(this.z);
        return result;
    }

    /**
     * Multiply this point values by a given value.
     *
     * @param scalar Value to multiply.
     * @return A new point with the multiplied values.
     */
    public Point3D multiply(final float scalar) {
        return new Point3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * @return This point with rounded values.
     */
    public Point3D rounded() {
        return new Point3D(Math.round(this.x), Math.round(this.y), Math.round(this.z));
    }

    /**
     * Subtract this point values by the other point values.
     *
     * @param point Other point to use.
     * @return A new Point3D with the computed values.
     */
    public Point3D subtract(final Point3D point) {
        return new Point3D(this.x - point.x, this.y - point.y, this.z - point.z);
    }

    @Override
    public String toString() {
        return this.x + Literals.VECTOR_SEPARATOR + this.y + Literals.VECTOR_SEPARATOR + this.z;
    }

    /**
     * @return A copy of this point with 0 as Y coordinate.
     */
    public Point3D toXZ() {
        return new Point3D(this.x, 0, this.z);
    }
}
