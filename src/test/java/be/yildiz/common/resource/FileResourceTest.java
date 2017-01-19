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

import be.yildiz.common.collections.Lists;
import be.yildiz.common.exeption.ResourceCorruptedException;
import be.yildiz.common.exeption.ResourceMissingException;
import be.yildiz.common.util.Literals;
import be.yildiz.common.util.Util;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class for FileResouce.
 *
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public final class FileResourceTest {

    public static class FileResourceBasic {

        @Rule
        public ExpectedException rule = ExpectedException.none();

        /***/
        @Test
        public void testFileResource() {
            String nonExstingFile = "none.none";
            this.rule.expect(ResourceMissingException.class);
            new FileResource(nonExstingFile);
            String existingFile = "test.log";
            final int size = 18;
            final int crc = 331922151;
            String content = "blablabla";
            File file = new File(existingFile);
            try {
                file.createNewFile();
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
            FileResource f = null;
            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                raf.writeChars(content);
                f = new FileResource(existingFile);
                Assert.assertEquals(size, f.getSize());
                String check = existingFile + Literals.TOSTRING_SEPARATOR + crc + Literals.TOSTRING_SEPARATOR + size;
                this.rule.expect(IndexOutOfBoundsException.class);
                f.check(check);
                f.deleteFile();
            } catch (ResourceMissingException e) {
                Assert.fail("The file exist, " + "an RessourceMissingException " + "should not have been thrown.");
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                raf.writeChars(content);
                final int badCrc = 33122151;
                f = new FileResource(existingFile);
                Assert.assertEquals(size, f.getSize());

                String check = existingFile + Literals.TOSTRING_SEPARATOR + badCrc + Literals.TOSTRING_SEPARATOR + size;
                f.check(check);
                Assert.fail("the crc is bad, check " + "should have thrown an exception");
            } catch (ResourceMissingException e) {
                Assert.fail("The file exist, " + "an RessourceMissingException " + "should not have been thrown.");
            } catch (ResourceCorruptedException e) {
                // ok
                f.deleteFile();
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
        }

        @Test(expected = NullPointerException.class)
        public void constructorFileNull() {
            new FileResource(null, FileResource.FileType.FILE);
        }

        @Test(expected = NullPointerException.class)
        public void constructorFileEmpty() {
            new FileResource("", FileResource.FileType.FILE);
        }

        @Test(expected = NullPointerException.class)
        public void constructorTypeNull() {
            new FileResource("file.txt", null);
        }

        @Test(expected = ResourceMissingException.class)
        public void constructorFileNotExistFailCreate() {
            String path;
            if(Util.isLinux()) {
                path = "/dev/null/f.txt";
            } else {
                path = "V:\f.txt";
            }
            new FileResource(path, FileResource.FileType.FILE);
        }
    }

    private static File getFile(String name) {
        return new File(FileResourceTest.class.getClassLoader().getResource(name).getFile()).getAbsoluteFile();
    }

    public static class FindResource {

        @Test
        public void happyFlow() {
            FileResource.findResource(getFile("test.properties").getAbsolutePath());
        }

        @Test(expected = ResourceMissingException.class)
        public void notExisting() {
            FileResource.findResource("azerty");
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            FileResource.findResource(null);
        }
    }


    public static class createFile {

        @Rule
        public TemporaryFolder folder = new TemporaryFolder();

        @Test
        public void happyFlow() throws IOException {
            folder.create();
            String file = folder.getRoot().getAbsolutePath() + "/test.txt";
            Assert.assertFalse(new File(file).exists());
            FileResource.createFile(file);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isFile());
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            FileResource.createFile(null);
        }

        @Test
        public void alreadyExisting() throws IOException {
            folder.create();
            String file = getFile("test-resource.txt").getAbsolutePath();
            Assert.assertTrue(new File(file).exists());
            long size = new File(file).length();
            FileResource f = FileResource.createFile(file);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isFile());
            Assert.assertEquals(size, f.getSize());
        }
    }

    public static class createDirectory {
        @Rule
        public TemporaryFolder folder = new TemporaryFolder();

        @Test
        public void happyFlow() throws IOException {
            folder.create();
            String file = folder.getRoot().getAbsolutePath() + "/test";
            Assert.assertFalse(new File(file).exists());
            FileResource.createDirectory(file);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isDirectory());
        }

        @Test(expected = NullPointerException.class)
        public void fromNull() {
            FileResource.createDirectory(null);
        }

        @Test
        public void alreadyExisting() throws IOException {
            folder.create();
            String file = folder.newFolder().getAbsolutePath();
            Assert.assertTrue(new File(file).exists());
            FileResource.createDirectory(file);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isDirectory());
        }
    }

    public static class createResource {

        @Rule
        public TemporaryFolder folder = new TemporaryFolder();

        @Test
        public void file() throws IOException {
            folder.create();
            String file = getFile("test-resource.txt").getAbsolutePath();
            Assert.assertTrue(new File(file).exists());
            long size = new File(file).length();
            FileResource f = FileResource.createFileResource(file, FileResource.FileType.FILE);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isFile());
            Assert.assertEquals(size, f.getSize());
        }

        @Test
        public void directory() throws IOException {
            folder.create();
            String file = folder.getRoot().getAbsolutePath() + "/test";
            Assert.assertFalse(new File(file).exists());
            FileResource.createFileResource(file, FileResource.FileType.DIRECTORY);
            Assert.assertTrue(new File(file).exists());
            Assert.assertTrue(new File(file).isDirectory());
        }

        @Test(expected = NullPointerException.class)
        public void fromNullName() {
            FileResource.createFileResource(null, FileResource.FileType.FILE);
        }

        @Test(expected = NullPointerException.class)
        public void fromNullType() {
            FileResource.createFileResource("ok", null);
        }
    }

    public static class ListFile {

        @Test
        public void happyFlow() throws IOException{
            String file = getFile("fileresource-listfiles").getAbsolutePath();
            FileResource f = FileResource.findResource(file);
            List<FileResource> result = Lists.newList();
            f.listFile(result);
            Assert.assertEquals(2, result.size());
            List<String> names = result.stream()
                    .map(FileResource::getName)
                    .collect(Collectors.toList());
            Assert.assertTrue(names.contains(file + File.separator + "file1.txt") && names.contains(file + File.separator + "file2.txt"));
        }

        @Test
        public void withIgnoredFile() throws IOException{
            String file = getFile("fileresource-listfiles").getAbsolutePath();
            FileResource f = FileResource.findResource(file);
            List<FileResource> result = Lists.newList();
            f.listFile(result, "file1.txt");
            Assert.assertEquals(1, result.size());
            Assert.assertEquals(file + File.separator + "file2.txt", result.get(0).getName());
        }
    }
}
