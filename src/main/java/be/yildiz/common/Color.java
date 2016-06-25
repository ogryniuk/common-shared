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

import java.io.Serializable;

/**
 * Class representing RGBA color.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class Color implements Serializable {

    /**
     * Black constant.
     */
    public static final Color BLACK = new Color(0, 0, 0);
    /**
     * Max value for a color parameter(255).
     */
    static final int MAX_VALUE = 255;
    /**
     * White constant.
     */
    public static final Color WHITE = new Color(Color.MAX_VALUE, Color.MAX_VALUE, Color.MAX_VALUE);
    /**
     * Red constant.
     */
    public static final Color RED = new Color(Color.MAX_VALUE, 0, 0);
    /**
     * Green constant.
     */
    public static final Color GREEN = new Color(0, Color.MAX_VALUE, 0);
    /**
     * Blue constant.
     */
    public static final Color BLUE = new Color(0, 0, Color.MAX_VALUE);
    /**
     * Yellow constant.
     */
    public static final Color YELLOW = new Color(Color.MAX_VALUE, Color.MAX_VALUE, 0);
    /**
     * Orange constant.
     */
    public static final Color ORANGE = new Color(Color.MAX_VALUE, 70, 0);
    /**
     * Gray constant.
     */
    public static final Color GRAY = new Color(127, 127, 127, Color.MAX_VALUE);
    /**
     * Min value for a color parameter(0).
     */
    static final int MIN_VALUE = 0;
    /***/
    private static final long serialVersionUID = -2169198600911019532L;
    /**
     * Red value, must be between MIN_VALUE and MAX_VALUE.
     */
    public final int red;

    /**
     * Green value, must be between MIN_VALUE and MAX_VALUE.
     */
    public final int green;

    /**
     * Blue value, must be between MIN_VALUE and MAX_VALUE.
     */
    public final int blue;

    /**
     * Alpha value, must be between MIN_VALUE and MAX_VALUE.
     */
    public final int alpha;

    /**
     * Red value, must be between 0 and 1.
     */
    public final float normalizedRed;

    /**
     * Green value, must be between 0 and 1.
     */
    public final float normalizedGreen;

    /**
     * Blue value, must be between 0 and 1.
     */
    public final float normalizedBlue;

    /**
     * Alpha value, must be between 0 and 1.
     */
    public final float normalizedAlpha;

    /**
     * Full constructor, alpha is set to MAX_VALUE.
     *
     * @param value Value for red, green and blue(must be between MIN_VALUE and
     *              MAX_VALUE).
     */
    public Color(final int value) {
        this(value, value, value, Color.MAX_VALUE);
    }

    /**
     * Full constructor, alpha is set to MAX_VALUE.
     *
     * @param redValue   Red value(must be between MIN_VALUE and MAX_VALUE).
     * @param greenValue Green value(must be between MIN_VALUE and MAX_VALUE).
     * @param blueValue  Blue value(must be between MIN_VALUE and MAX_VALUE).
     */
    public Color(final int redValue, final int greenValue, final int blueValue) {
        this(redValue, greenValue, blueValue, Color.MAX_VALUE);
    }

    /**
     * Full constructor.
     *
     * @param redValue   Red value(must be between MIN_VALUE and MAX_VALUE).
     * @param greenValue Green value(must be between MIN_VALUE and MAX_VALUE).
     * @param blueValue  Blue value(must be between MIN_VALUE and MAX_VALUE).
     * @param alphaValue Alpha value (must be between MIN_VALUE and MAX_VALUE).
     */
    public Color(final int redValue, final int greenValue, final int blueValue, final int alphaValue) {
        super();
        this.red = Color.checkValue(redValue);
        this.green = Color.checkValue(greenValue);
        this.blue = Color.checkValue(blueValue);
        this.alpha = Color.checkValue(alphaValue);
        this.normalizedRed = this.red / (float) Color.MAX_VALUE;
        this.normalizedBlue = this.blue / (float) Color.MAX_VALUE;
        this.normalizedGreen = this.green / (float) Color.MAX_VALUE;
        this.normalizedAlpha = this.alpha / (float) Color.MAX_VALUE;
    }

    /**
     * Check if the value is between MIN_VALUE and MAX_VALUE.
     *
     * @param value Value to check.
     * @return MIN_VALUE if value is <=MIN_VALUE, MAX_VALUE if value >=
     * MAX_VALUE or value if it is between MIN_VALUE and MAX_VALUE.
     */
    private static int checkValue(final int value) {
        if (value >= Color.MAX_VALUE) {
            return Color.MAX_VALUE;
        } else if (value <= Color.MIN_VALUE) {
            return Color.MIN_VALUE;
        } else {
            return value;
        }
    }

    /**
     * Create a copy of this color with a value added to RGB values, alpha is
     * not updated.
     *
     * @param value Value to add.
     * @return A new color with the same value as this one added to the given
     * value.
     */
    public Color add(final int value) {
        return new Color(this.red + value, this.green + value, this.blue + value, this.alpha);
    }

    public Color add(final int red, final int green, final int blue) {
        return new Color(this.red + red, this.green + green, this.blue + blue, this.alpha);
    }

    /**
     * Test equality between the RGBA values.
     *
     * @param obj Other object to test.
     * @return true if the 2 object have RGBA identical values.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return false;
        }
        Color other = (Color) obj;
        return this.blue == other.blue && this.green == other.green && this.red == other.red && this.alpha == other.alpha;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.blue;
        result = prime * result + this.green;
        result = prime * result + this.red;
        result = prime * result + this.alpha;
        return result;
    }

    /**
     * @return red value + ',' + green value + ',' + blue value + ',' + alpha.
     */
    @Override
    public String toString() {
        return this.red + "," + this.green + "," + this.blue + "," + this.alpha;
    }
}
