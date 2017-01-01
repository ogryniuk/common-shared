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

package be.yildiz.common.shape;

import be.yildiz.common.util.Checker;
import lombok.EqualsAndHashCode;

/**
 * Contains a plane coordinates.
 *
 * @author Grégory Van den Borre
 */
@EqualsAndHashCode
public final class Plane {

    /**
     * Width size of the plane.
     */
    public final int width;

    /**
     * Depth size of the plane.
     */
    public final int depth;

    /**
     * Full constructor.
     *
     * @param widthSize Width size of the box.
     * @param depthSize Depth size of the box.
     */
    public Plane(final int widthSize, final int depthSize) {
        super();
        Checker.exceptionNotGreaterThanZero(widthSize);
        Checker.exceptionNotGreaterThanZero(depthSize);
        this.width = widthSize;
        this.depth = depthSize;
    }

    /**
     * Full constructor, create a square.
     *
     * @param size Size for the width and depth
     */
    public Plane(final int size) {
        this(size, size);
    }

    @Override
    public String toString() {
        return "Plane(" + this.width + ", " + this.depth + ")";
    }

}
