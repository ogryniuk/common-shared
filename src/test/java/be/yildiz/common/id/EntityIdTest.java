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

package be.yildiz.common.id;

import be.yildiz.common.collections.Sets;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

/**
 * Test class for EntityId.
 * @author Grégory Van den Borre
 */
public final class EntityIdTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    /***/
    @Test
    public void testEquals() {
        final long value = 5;
        Assert.assertEquals(EntityId.get(value), EntityId.get(value));
        Assert.assertNotEquals(EntityId.get(value), EntityId.get(value + 1));
        Set< EntityId > set = Sets.newSet();
        Assert.assertTrue(set.add(EntityId.get(value)));
        Assert.assertFalse(set.add(EntityId.get(value)));
        Assert.assertTrue(set.remove(EntityId.get(value)));
        Assert.assertTrue(set.add(EntityId.get(value)));
        Assert.assertNotEquals(null, EntityId.get(value));
    }

    /***/
    @Test
    public void testIsNegative() {
        Assert.assertTrue(EntityId.get(-5L).isNegative());
        Assert.assertFalse(EntityId.get(0L).isNegative());
        Assert.assertFalse(EntityId.get(5L).isNegative());
    }

    /***/
    @Test
    public void testIsWorld() {
        final long value = EntityId.WORLD.value;
        Assert.assertTrue(EntityId.get(value).isWorld());
        Assert.assertTrue(EntityId.isWorld(value));
        Assert.assertTrue(EntityId.isWorld(EntityId.WORLD));
        Assert.assertFalse(EntityId.get(5L).isWorld());
        Assert.assertFalse(EntityId.isWorld(1));
        Assert.assertFalse(EntityId.isWorld(EntityId.get(1L)));
        this.rule.expect(NullPointerException.class);
        EntityId.isWorld(null);
    }

    /***/
    @Test
    public void testToString() {
        Assert.assertEquals("5", EntityId.get(5L).toString());
    }
}
