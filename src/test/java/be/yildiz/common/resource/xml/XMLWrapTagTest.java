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

package be.yildiz.common.resource.xml;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public class XMLWrapTagTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void testGenerate() {
        XMLWrapTag tag = new XMLWrapTag("name");
        String result = "<name></name>";
        Assert.assertEquals(result, tag.generate(new StringBuilder()));
        tag.addChild(new XMLValueTag("child1", "c1value"));
        result = "<name><child1>c1value</child1></name>";
        Assert.assertEquals(result, tag.generate(new StringBuilder()));
        tag.addChild(new XMLValueTag("child2", "c2value"));
        result = "<name><child1>c1value</child1><child2>c2value</child2></name>";
        Assert.assertEquals(result, tag.generate(new StringBuilder()));
    }

    @Test
    public void testXMLWrapTag() {
        XMLWrapTag tag = new XMLWrapTag("name");
        Assert.assertEquals("name", tag.getName());
        this.rule.expect(NullPointerException.class);
        tag = new XMLWrapTag(null);
    }
}
