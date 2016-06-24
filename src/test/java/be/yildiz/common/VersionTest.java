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

import be.yildiz.common.Version.VersionType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public final class VersionTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void testVersionMajor() {
        this.rule.expect(IllegalArgumentException.class);
        new Version(VersionType.RELEASE, -1, 1, 1, 0);
    }

    @Test
    public void testVersionMinor() {
        this.rule.expect(IllegalArgumentException.class);
        new Version(VersionType.RELEASE, 1, -1, 1, 0);
    }

    @Test
    public void testVersionSub() {
        this.rule.expect(IllegalArgumentException.class);
        new Version(VersionType.RELEASE, 1, 1, -1, 0);
    }

    @Test
    public void testVersionRev() {
        this.rule.expect(IllegalArgumentException.class);
        new Version(VersionType.RELEASE, 1, 1, 1, -1);
    }

    @Test
    public void testGetMajor() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals(i1, v.getMajor());
    }

    @Test
    public void testGetMinor() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals(i2, v.getMinor());
    }

    @Test
    public void testGetSub() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals(i3, v.getSub());
    }

    @Test
    public void testGetRev() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals(r, v.getRev());
    }

    @Test
    public void testEqualsObject() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Version v2 = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals(v, v2);
        v2 = new Version(VersionType.RELEASE, i3, i1, i2, r);
        Assert.assertNotEquals(v, v2);
        Assert.assertNotEquals("String", v);
        Assert.assertNotEquals(null, v);
    }

    @Test
    public void testHashCode() {
        final Integer i1 = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);
        final int r = 5;
        final Version v1 = new Version(VersionType.RELEASE, i1.intValue(), i2.intValue(), i3.intValue(), r);
        final Version v2 = new Version(VersionType.RELEASE, i1.intValue(), i2.intValue(), i3.intValue(), r);
        Assert.assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testToString() {
        final int i1 = 1;
        final int i2 = 2;
        final int i3 = 3;
        final int r = 5;
        final Version v = new Version(VersionType.RELEASE, i1, i2, i3, r);
        Assert.assertEquals("RELEASE 1.2.3_5", v.toString());
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(VersionType.RELEASE, new Version(VersionType.RELEASE, 0, 0, 0, 0).getType());
        Assert.assertEquals(VersionType.BETA, new Version(VersionType.BETA, 0, 0, 0, 0).getType());
        Assert.assertEquals(VersionType.ALPHA, new Version(VersionType.ALPHA, 0, 0, 0, 0).getType());
    }
}
