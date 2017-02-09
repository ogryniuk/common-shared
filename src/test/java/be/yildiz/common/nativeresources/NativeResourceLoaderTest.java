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

package be.yildiz.common.nativeresources;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;

import static be.yildiz.common.nativeresources.NativeResourceLoader.*;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class NativeResourceLoaderTest {

    public static class GetLibPath {

        @Test
        public void withExistingFileWithExtension() {
            File f = getFile("lib_out" + NativeResourceLoader.LIBRARY_EXTENSION);
            Assert.assertEquals(f.getAbsolutePath(), NativeResourceLoader.getLibPath(f.getAbsolutePath()));
        }

        @Test
        public void withExistingFileWithoutExtension() {
            File f = getFile("lib_out" + NativeResourceLoader.LIBRARY_EXTENSION);
            Assert.assertEquals(f.getAbsolutePath(), NativeResourceLoader.getLibPath(f.getAbsolutePath().replace(LIBRARY_EXTENSION, "")));
        }

        @Test
        public void withNotExistingFileRegistered() {
            System.setProperty("java.class.path", System.getProperty("java.class.path", "") + File.pathSeparator + getFile("test.jar").getAbsolutePath());
            Assert.assertEquals(LIB_DIRECTORY + File.separator + DIRECTORY + File.separator + "lib" + LIBRARY_EXTENSION, NativeResourceLoader.getLibPath("lib"));
        }

        @Test
        @Ignore("cannot pass since context is static, lib is registered while should not")
        public void withNotExistingFileNotRegistered() {
            Assert.assertEquals(null, NativeResourceLoader.getLibPath("lib"));
        }

        @Test(expected = AssertionError.class)
        public void withNullFilePath() {
            NativeResourceLoader.getLibPath(null);
        }
    }

    private static File getFile(String name) {
        return new File(NativeResourceLoader.class.getClassLoader().getResource(name).getFile()).getAbsoluteFile();
    }
}
