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

package be.yildiz.common.gameobject;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;

/**
 * Common part for the ClientGameEntity and the ServerGameEntity, this interface is not meant to be used but to be extended by usable client and server interfaces.
 *
 * @author Grégory Van den Borre
 */
public interface GameMaterialization extends Movable {

    /**
     * @return The object associated Id.
     */
    EntityId getId();

    /**
     * Rotate the object.
     *
     * @param x Rotation quaternion x value.
     * @param y Rotation quaternion y value.
     * @param z Rotation quaternion z value.
     * @param w Rotation quaternion w value.
     */
    void rotate(float x, float y, float z, float w);

    /**
     * Scale the object.
     *
     * @param scale Scale factor, i e to scale 10% bigger, set 1.1.
     */
    default void scale(final float scale) {
        this.scale(scale, scale, scale);
    }

    default void resetScale() {
        Point3D scale = this.getScaleSize();
        this.scale(1.0f / scale.x, 1.0f / scale.y, 1.0f / scale.z);
    }

    /**
     * @return The current object scaling.
     */
    Point3D getScaleSize();

    /**
     * Scale the object, scale is relative to latest size(scale to 0.5f to remove 50% of the size, then 0.5f again to remove 50 additional percent to the 50% of the original, size will then be 25% of
     * the original size).
     *
     * @param x Scale X factor, i e to scale 10% bigger, set 1.1.
     * @param y Scale Y factor.
     * @param z Scale Z factor.
     */
    void scale(float x, float y, float z);

    /**
     * Delete the graphic/physic object.
     */
    @Override
    void delete();

    void sleep(boolean b);
}
