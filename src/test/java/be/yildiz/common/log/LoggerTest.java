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

package be.yildiz.common.log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Scanner;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class LoggerTest {

    public static class Info {

        @Test
        public void happyFlow() throws Exception {
            File tmp = new File("infoHappyFlow.log");
            tmp.createNewFile();
            Logger.setFile(tmp.getName());
            Logger.setLevelInfo();
            Logger.info("test");
            try (Scanner scanner = new Scanner(tmp)) {
                String entry = scanner.nextLine();
                Assert.assertTrue(entry.startsWith("INFOS:"));
                Assert.assertTrue(entry.endsWith("test"));
            } finally {
                tmp.deleteOnExit();
            }
        }

        @Test
        public void debug() throws Exception {
            File tmp = new File("infoDebug.log");
            tmp.createNewFile();
            Logger.setFile(tmp.getName());
            Logger.setLevelInfo();
            Logger.debug("this should not be printed");
            try (Scanner scanner = new Scanner(tmp)) {
                Assert.assertFalse(scanner.hasNext());
            } finally {
                tmp.deleteOnExit();
            }
        }

    }

    public static class Debug {

        @Test
        public void happyFlow() throws Exception {
            File tmp = new File("debugHappyFlow.log");
            tmp.createNewFile();
            Logger.setFile(tmp.getName());
            Logger.setLevelDebug();
            Logger.debug("test");
            try (Scanner scanner = new Scanner(tmp)) {
                String entry = scanner.nextLine();
                Assert.assertTrue(entry.startsWith("DEBUG:"));
                Assert.assertTrue(entry.endsWith("test"));
            } finally {
                tmp.deleteOnExit();
            }
        }

    }
}