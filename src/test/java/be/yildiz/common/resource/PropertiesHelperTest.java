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

package be.yildiz.common.resource;

import be.yildiz.common.exeption.ResourceMissingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

/**
 * @author Grégory Van den Borre
 */
public class PropertiesHelperTest {

    @Test
    public void testGetPropertiesFromFile() {
        Properties p = PropertiesHelper.getPropertiesFromFile(new File("src/test/resources/test.properties"));
        Assert.assertEquals(p.getProperty("key1"), "value1");
        Assert.assertEquals(p.getProperty("key2"), "value2");
        Assert.assertEquals(p.getProperty("key3"), "value3");
    }

    @Test(expected = ResourceMissingException.class)
    public void testGetPropertiesFromFileNotExist() {
        PropertiesHelper.getPropertiesFromFile(new File("idonotexists.properties"));
    }

    @Test
    public void testGetPropertiesFromFileWithArgs() {
        String[] args = {"key2=value123456"};
        Properties p = PropertiesHelper.getPropertiesFromFile(new File("src/test/resources/test.properties"), args);
        Assert.assertEquals(p.getProperty("key1"), "value1");
        Assert.assertEquals(p.getProperty("key2"), "value123456");
        Assert.assertEquals(p.getProperty("key3"), "value3");
    }

    @Test
    public void testGetPropertiesFromFileWithArgsNull() {
        String[] args = {"key1=value1234567", null};
        Properties p = PropertiesHelper.getPropertiesFromFile(new File("src/test/resources/test.properties"), args);
        Assert.assertEquals(p.getProperty("key1"), "value1234567");
    }

    @Test
    public void testGetPropertiesFromFileWithArgsInvalid() {
        String[] args = {"key1:value1234567"};
        Properties p = PropertiesHelper.getPropertiesFromFile(new File("src/test/resources/test.properties"), args);
        Assert.assertEquals(p.getProperty("key1"), "value1");
    }

    @Test
    public void testGetBooleanDefault() {
        Properties p = PropertiesHelper.getPropertiesFromFile(new File("src/test/resources/test.properties"));
        Assert.assertFalse(PropertiesHelper.getBooleanValue(p, "notExistingKey", false));
        Assert.assertTrue(PropertiesHelper.getBooleanValue(p, "notExistingKey", true));
    }

}
