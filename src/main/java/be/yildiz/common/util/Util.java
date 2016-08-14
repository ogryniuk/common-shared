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

package be.yildiz.common.util;

import be.yildiz.common.collections.Lists;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * Utility class to provide basic services.
 *
 * @author Grégory Van den Borre
 */
public interface Util {

    /**
     * Random used to generate numbers.
     */
    Random RANDOM = new Random();

    /**
     * Compare 2 set, and return a list with the elements contained in the first
     * set but not in the second.
     *
     * @param <T>    Contained objects type.
     * @param first  Set to compare.
     * @param second Other set to compare.
     * @return a List with the difference between the first and second set.
     */
    static <T> List<T> compareSet(final Set<T> first, final Set<T> second) {
        List<T> onlyInFirstSet = Lists.newList();
        for (T o : first) {
            if (!second.contains(o)) {
                onlyInFirstSet.add(o);
            }
        }
        return onlyInFirstSet;
    }

    /**
     * Delete a folder and its content.
     *
     * @param folder Folder to delete.
     */
    static void deleteFolder(final File folder) {
        File[] files = folder.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                Util.deleteFolder(f);
            } else {
                f.delete();
            }
        }
        folder.delete();
    }

    /**
     * Test equality on two float.
     *
     * @param d1 float to test.
     * @param d2 Other float to test.
     * @return True is f1 and f2 are considered equals.
     */
    static boolean equalFloat(final float d1, final float d2) {
        return Float.compare(d1, d2) == 0;
    }

    /**
     * Run an external application from a given directory.
     *
     * @param applicationName  Full application name, with its extension.
     * @param workingDirectory Root is considered as calling application working directory.
     * @throws IOException Exception thrown from the runtime exec method.
     */
    static void execute(final String applicationName, final String workingDirectory) throws IOException {
        Runtime.getRuntime().exec(new String[]{new File(workingDirectory + File.separator + applicationName).getAbsolutePath()}, null,
                new File(workingDirectory).getAbsoluteFile());
    }

    /**
     * Convert a float to an integer by removing values after comma.
     *
     * @param value Original float value.
     * @return The integer value.
     */
    static int floatToInt(final float value) {
        return Float.valueOf(value).intValue();
    }

    /**
     * Compute a random number.
     *
     * @return A random number.
     */
    static int getRandom() {
        return Util.RANDOM.nextInt();
    }

    /**
     * Compute a random number with max value.
     *
     * @param maxIncluded Max value to use(included in result).
     * @return A random number.
     */
    static int getRandom(final int maxIncluded) {
        return Util.RANDOM.nextInt(maxIncluded + 1);
    }

    /**
     * Check if a parameter is greater than 0, if not, an InvalidParameterException is
     * thrown.
     *
     * @param param Parameter to check
     */
    static void greaterThanZero(final float param) {
        if (param <= 0) {
            throw new InvalidParameterException("Parameter cannot be <= 0, current value is " + param);
        }
    }

    /**
     * @return true if the current operating system is Linux.
     */
    static boolean isLinux() {
        return "linux".equals(System.getProperty("os.name").toLowerCase(Locale.ENGLISH));
    }

    /**
     * @return true If the platform is X86.
     */
    static boolean isX86() {
        return "x86".equals(System.getProperty("os.arch"));
    }

    /**
     * Check if a value is not bigger than its allowed limit.
     *
     * @param value    Value to check.
     * @param maxValue Maximum value.
     * @return The value if it is smaller than the maximum, else return the
     * maximum.
     */
    static float setLimitedValue(final float value, final float maxValue) {
        if (value < maxValue) {
            return value;
        }
        return maxValue;
    }

    /**
     * Return a value or 0 if negative.
     *
     * @param value Value to check.
     * @return The value.
     */
    static float setPositiveValue(final float value) {
        if (value < 0) {
            return 0;
        }
        return value;
    }

    /**
     * Return a value in a range.
     *
     * @param value Value to check.
     * @param min   Minimum value.
     * @param max   Maximum value.
     * @return The value.
     */
    static float setValue(final float value, final float min, final float max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Return a value in a range.
     *
     * @param value Value to check.
     * @param min   Minimum value.
     * @param max   Maximum value.
     * @return The value.
     */
    static int setValue(final int value, final int min, final int max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * @return The application PID value.
     */
    static String getPid() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }
}
