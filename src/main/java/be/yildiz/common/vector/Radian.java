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

/**
 * This class represent a radian angle.
 *
 * @author Grégory Van den Borre
 */
public final class Radian {

    /**
     * Value of the angle.
     */
    public final float angle;

    /**
     * Simple constructor set the angle at 0.
     */
    public Radian() {
        this(0);
    }

    /**
     * Full constructor.
     *
     * @param radian initialize the radiant value.
     */
    public Radian(final float radian) {
        super();
        this.angle = radian;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Radian)) {
            return false;
        }
        Radian other = (Radian) obj;
        return Float.floatToIntBits(this.angle) == Float.floatToIntBits(other.angle);
    }

    /**
     * @return The angle value.
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        int temp;
        temp = Float.floatToIntBits(this.angle);
        result = prime * result + temp;
        return result;
    }

    /**
     * @return The angle value.
     */
    @Override
    public String toString() {
        return "Radian value: " + this.angle;
    }
}
