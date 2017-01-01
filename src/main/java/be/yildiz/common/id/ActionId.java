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

package be.yildiz.common.id;

import be.yildiz.common.collections.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * An id represent an unique instance of a class of object. The same id can be shared between different class but two objects of the same class cannot have the same id.
 *
 * @author Grégory Van den Borre
 */
public class ActionId implements Serializable {

    /***/
    private static final long serialVersionUID = -1300879297702695817L;

    /**
     * List of all registered id with their value as key.
     */
    private static final Map<Integer, ActionId> LIST = Maps.newMap();

    /**
     * Constant value for the world.
     */
    private static final int WORLD_VALUE = 0;
    /**
     * Constant id for the world.
     */
    public static final ActionId WORLD = new ActionId(ActionId.WORLD_VALUE);

    /**
     * Id value, it is immutable.
     */
    public final int value;

    /**
     * Precomputed hashcode.
     */
    private final int hashCode;

    /**
     * Full constructor, private to prevent use, to create an id, retrieve it from Id.get.
     *
     * @param idValue Initialize the wrapped value.
     */
    protected ActionId(final int idValue) {
        super();
        this.value = idValue;
        ActionId.LIST.putIfAbsent(this.value, this);
        this.hashCode = Long.valueOf(this.value).hashCode();
    }

    /**
     * Retrieve an Id from a value.
     *
     * @param value Id value to get, positive and negative values are allowed, -1 is WORLD.
     * @return The Id with the internal value correspond to the parameter.
     */
    public static ActionId get(final int value) {
        if (!ActionId.LIST.containsKey(value)) {
            ActionId.LIST.put(value, new ActionId(value));
        }
        return ActionId.LIST.get(value);
    }

    /**
     * Check if an id is world.
     *
     * @param id Id to check.
     * @return <code>true</code> if Id matches world Id.
     */
    public static boolean isWorld(final ActionId id) {
        return id.value == ActionId.WORLD_VALUE;
    }

    /**
     * Check if an id is world.
     *
     * @param id Internal value to check.
     * @return <code>true</code> if the internal value matches world internal value.
     */
    public static boolean isWorld(final long id) {
        return id == ActionId.WORLD_VALUE;
    }

    /**
     * @return <code>true</code> if id value is lower than 0.
     */
    public boolean isNegative() {
        return this.value < 0;
    }

    /**
     * Check if this is world.
     *
     * @return <code>true</code> if this id is world.
     */
    public boolean isWorld() {
        return this.value == ActionId.WORLD_VALUE;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ActionId)) {
            throw new IllegalArgumentException("Not an action id:" + (obj == null ? "null" : obj.getClass()));
        }
        return this == obj;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /**
     * @return Id value as a String.
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
