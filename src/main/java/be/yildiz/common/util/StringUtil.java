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

package be.yildiz.common.util;

import java.util.Collection;

/**
 * Utility class for manipulating String object and building toString method.
 *
 * @author Grégory Van den Borre
 */
public interface StringUtil {

    /**
     * Build a string from an array. Every object contained has its toString
     * result separated by |.
     *
     * @param array Array to use.
     * @return A string containing all the array values.
     */
    static String arrayToString(final float[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o.toString());
            sb.append('|');
        }
        return sb.toString();
    }

    /**
     * Build a string from an array. Every object contained has its toString
     * result separated by |.
     *
     * @param array Array to use.
     * @return A string containing all the array values.
     */
    static String arrayToString(final Object[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o.toString());
            sb.append('|');
        }
        return sb.toString();
    }

    /**
     * Build a random String from a base, useful for unique names,...
     *
     * @param base Object to use as base.
     * @return the base Object.toString with random suffix(based on Math.random
     * and System.nanoTime).
     */
    static String buildRandomString(final Object base) {
        return StringUtil.buildRandomString(base.toString());
    }

    /**
     * Build a random String from a base, useful for unique names,...
     *
     * @param base String to use as base.
     * @return the base String with random suffix(based on Math.random and
     * System.nanoTime).
     */
    static String buildRandomString(final String base) {
        final StringBuilder sb = new StringBuilder(base);
        sb.append(Literals.TOSTRING_SEPARATOR);
        sb.append(System.nanoTime());
        sb.append(Literals.TOSTRING_SEPARATOR);
        sb.append(Math.random());
        return sb.toString();
    }

    /**
     * Build a String from a list of objects.
     *
     * @param objects Objects to use.
     * @return The string built from the parameters.
     */
    static String buildToString(final Object... objects) {
        final StringBuilder sb = new StringBuilder();
        for (Object object : objects) {
            if (object == null) {
                sb.append("null");
            } else {
                sb.append(object.toString());
            }
            sb.append(Literals.TOSTRING_SEPARATOR);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static String fillVariable(String base, Object[] replacement) {
        StringBuilder result = new StringBuilder(base);
        for (int i = 0; i < replacement.length; i++) {
            String name = "${" + i + "}";
            int index = result.indexOf(name);
            while (index != -1) {
                result.replace(index, index + name.length(), replacement[i].toString());
                index = result.indexOf(name);
            }
        }
        return result.toString();
    }

    /**
     * Convert a time(in milliseconds) into a formated time(h m s).
     *
     * @param time Time value in millisecond to format.
     * @return A formated String with the parameter value displayed in h m s.
     */
    static String formatTime(final long time) {
        TimeConverter timeConverter = new TimeConverter();
        timeConverter.setTime(time);
        int min = timeConverter.getMin();
        int sec = timeConverter.getSec();
        StringBuilder sb = new StringBuilder();
        if (min > 0) {
            sb.append(min);
            sb.append(" min ");
        }
        if (sec > 0) {
            sb.append(sec);
            sb.append(" sec ");
        }
        return sb.toString();
    }

    /**
     * Convert a time into a formated time(h m s).
     *
     * @param time Time value to format.
     * @return A formated String with the parameter value displayed in h m s.
     */
    static String formatTime(final Time time) {
        return StringUtil.formatTime(time.timeInMs);
    }

    /**
     * Insert a character at a given position in a string.
     *
     * @param string   String to use.
     * @param position Position to insert the character.
     * @param c        Character to insert.
     * @return The modified string.
     */
    static String insertChar(final String string, final int position, final char c) {
        return new StringBuilder(string).insert(position, c).toString();
    }

    /**
     * Remove a char from a string at a given position.
     *
     * @param string   String to replace.
     * @param position Position of the char to remove, first index is 0.
     * @return The modified string.
     */
    static String removeChar(final String string, final int position) {
        if (position == string.length()) {
            return StringUtil.removeLastChar(string);
        }
        return string.substring(0, position) + string.substring(position + 1);
    }

    /**
     * Remove the first character in a string.
     *
     * @param string String to use.
     * @return The string without the first char.
     */
    static String removeFirstChar(final String string) {
        return string.substring(1, string.length());
    }

    /**
     * Remove the last char for a given String.
     *
     * @param string String to use.
     * @return The given String without the last char.
     */
    static String removeLastChar(final String string) {
        if (string.length() == 0) {
            return "";
        }
        return string.substring(0, string.length() - 1);
    }

    /**
     * Remove the last char for a given StringBuilder.
     *
     * @param string StringBuilder to use.
     * @return The given String without the last char.
     */
    static String removeLastChar(final StringBuilder string) {
        if (string.length() == 0) {
            return "";
        }
        return string.substring(0, string.length() - 1);
    }

    /**
     * Build a string from the collection. Every object contained has its
     * toString result separated by comma.
     *
     * @param <T> Collection type.
     * @param c   Collection to use.
     * @return A string containing all the collection values, or "null" if
     * parameter is <code>null</code>.
     */
    static <T> String toString(final Collection<T> c) {
        if (c == null) {
            return "null";
        } else if (c.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : c) {
            sb.append(t.toString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Call toString method of object and handle <code>null</code> cases by
     * returning "null".
     *
     * @param object Object to get String value.
     * @return The object toString value or "null" if the parameter is
     * <code>null</code>.
     */
    static String toString(final Object object) {
        if (object == null) {
            return "null";
        }
        return object.toString();
    }

    /**
     * Call toString method of object and handle <code>null</code> cases by
     * returning empty string.
     *
     * @param object Object to get String value.
     * @return The object toString value or "" if the parameter is
     * <code>null</code>.
     */
    static String safe(final Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }
}
