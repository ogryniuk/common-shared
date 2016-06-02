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

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Wrap the current version, immutable.
 * @author Grégory Van den Borre
 */
@EqualsAndHashCode
@Getter
public final class Version {

    /**
     * Major version number.
     */
    private final int major;

    /**
     * Minor version number.
     */
    private final int minor;

    /**
     * Sub version number.
     */
    private final int sub;

    /**
     * Revision number.
     */
    private final int rev;

    /**
     * Version type.
     */
    private final VersionType type;

    /**
     * Full constructor.
     * @param type
     *            Version type.
     * @param majorNumber
     *            Version major number.
     * @param minorVersion
     *            Version minor number.
     * @param subVersion
     *            Version sub number.
     * @param rev
     *            Revision number.
     */
    public Version(final VersionType type, final int majorNumber, final int minorVersion, final int subVersion, final int rev) {
        super();
        if (majorNumber < 0 || minorVersion < 0 || subVersion < 0 || rev < 0) {
            throw new IllegalArgumentException("Invalid version value: major:" + majorNumber + ", minor:" + minorVersion + ", sub:"
                    + subVersion);
        }
        this.type = type;
        this.major = majorNumber;
        this.minor = minorVersion;
        this.sub = subVersion;
        this.rev = rev;
    }

    /**
     * @return The major, minor and sub values separated with '.' and '_' for
     *         the revision.
     */
    @Override
    public String toString() {
        return this.type + " " + this.major + "." + this.minor + "." + this.sub + "_" + this.rev;
    }

    /**
     * Possible versions type.
     * @author Van den Borre Grégory
     */
    public enum VersionType {

        /**
         * Alpha version.
         */
        ALPHA,

        /**
         * Beta version.
         */
        BETA,

        /**
         * Release version.
         */
        RELEASE
    }

}
