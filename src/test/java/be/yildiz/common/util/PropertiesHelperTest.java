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

import be.yildiz.common.exeption.ResourceException;
import be.yildiz.common.exeption.ResourceMissingException;
import be.yildiz.common.resource.PropertiesHelper;
import be.yildiz.common.resource.ResourceUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * Test class for PropertiesHelper.
 *
 * @author Grégory Van den Borre
 */
public class PropertiesHelperTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @AfterClass
    public static void clean() {
        File ff = new File("locked.properties");
        ff.setWritable(true);
        ff.delete();
    }

    @Test
    public void testGetValue() {
        Properties p = new Properties();
        p.setProperty("aValue", ":)");
        Assert.assertEquals(":)", PropertiesHelper.getValue(p, "aValue"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueNotFound() {
        Properties p = new Properties();
        PropertiesHelper.getValue(p, "aValue");
    }

    @Test
    public void testGetBooleanValue() {
        Properties p = new Properties();
        p.setProperty("aValue", "true");
        Assert.assertTrue(PropertiesHelper.getBooleanValue(p, "aValue"));
        p.setProperty("aValue", "false");
        Assert.assertFalse(PropertiesHelper.getBooleanValue(p, "aValue"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBooleanValueNotFound() {
        Properties p = new Properties();
        PropertiesHelper.getBooleanValue(p, "aValue");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBooleanValueNotBoolean() {
        Properties p = new Properties();
        p.setProperty("aValue", ":)");
        PropertiesHelper.getBooleanValue(p, "aValue");
    }

    @Test
    public void testGetIntegerValue() {
        Properties p = new Properties();
        p.setProperty("aValue", "-1");
        Assert.assertEquals(-1, PropertiesHelper.getIntValue(p, "aValue"));
        p.setProperty("aValue", "0000");
        Assert.assertEquals(0, PropertiesHelper.getIntValue(p, "aValue"));
        p.setProperty("aValue", "17");
        Assert.assertEquals(17, PropertiesHelper.getIntValue(p, "aValue"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerValueNotFound() {
        Properties p = new Properties();
        PropertiesHelper.getIntValue(p, "aValue");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerValueNotInteger() {
        Properties p = new Properties();
        p.setProperty("aValue", ":)");
        PropertiesHelper.getIntValue(p, "aValue");
    }

    @Test
    public final void testGetPropertyFromFile() throws IOException {
        this.testGetPropertyFromFileName("f.properties");
    }

    @Test
    public final void testGetPropertyFromFileWithSpace() throws IOException {
        this.testGetPropertyFromFileName("f f.properties");
    }

    @Test
    public final void testGetPropertyFromFileWithUnicode() throws IOException {
        this.testGetPropertyFromFileName("éùriè$.properties");
    }

    @Test
    public final void testGetPropertyFromFileInDirectory() throws IOException {
        this.testGetPropertyFromFileName("test/f.properties");
    }

    private void testGetPropertyFromFileName(final String name) throws IOException {
        File file = new File(name);
        file.delete();
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Assert.assertTrue(file.createNewFile());

        Properties p = new Properties();
        p.setProperty("aValue", "something");
        try (Writer fileWriter = ResourceUtil.getFileWriter(file)) {
            p.store(fileWriter, "");
        } catch (IOException e) {
            throw new ResourceException("Configuration could not be saved in file " + file.getAbsolutePath());
        }

        Properties result = PropertiesHelper.getPropertiesFromFile(file);
        Assert.assertEquals("something", PropertiesHelper.getValue(result, "aValue"));

        Assert.assertTrue(file.delete());
    }

    @Test(expected = ResourceMissingException.class)
    public final void testGetPropertyFromFileNotFound() throws IOException {
        PropertiesHelper.getPropertiesFromFile(new File("nevermind.properties"));
    }

    @Test
    public void testSave() {
        Properties p = new Properties();
        p.setProperty("test", "aValue");
        File f = new File("pp.properties");
        PropertiesHelper.save(p, f);
        Assert.assertEquals("aValue", PropertiesHelper.getValue(PropertiesHelper.getPropertiesFromFile(f), "test"));
        f.delete();
    }

    @Test
    public void testSaveDirectory() {
        Properties p = new Properties();
        p.setProperty("test", "aDirectoryValue");
        File f = new File("test/pp.properties");
        PropertiesHelper.save(p, f);
        Assert.assertEquals("aDirectoryValue", PropertiesHelper.getValue(PropertiesHelper.getPropertiesFromFile(f), "test"));
        f.delete();
        f.getParentFile().delete();
    }

    @Test
    public void testSaveDirectoryWithSpace() {
        Properties p = new Properties();
        p.setProperty("test", "aSpaceValue");
        File f = new File("test test/pp.properties");
        PropertiesHelper.save(p, f);
        Assert.assertEquals("aSpaceValue", PropertiesHelper.getValue(PropertiesHelper.getPropertiesFromFile(f), "test"));
        f.delete();
        f.getParentFile().delete();
    }

    @Test
    public void testSaveUnicode() {
        Properties p = new Properties();
        p.setProperty("test", "aUnicodeValue");
        File f = new File("é@ùpoit test/µçé.properties");
        PropertiesHelper.save(p, f);
        Assert.assertEquals("aUnicodeValue", PropertiesHelper.getValue(PropertiesHelper.getPropertiesFromFile(f), "test"));
        f.delete();
        f.getParentFile().delete();
    }
}
