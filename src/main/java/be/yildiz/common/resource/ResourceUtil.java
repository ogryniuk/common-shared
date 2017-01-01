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
import be.yildiz.common.log.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Provide streams with a platform independent encoding.
 *
 * @author Grégory Van den Borre
 */
public final class ResourceUtil {

    /**
     * Encoding to use.
     */
    private static final Charset ENCODING = Charset.forName("UTF-8");

    /**
     * Private constructor to prevent use.
     */
    private ResourceUtil() {
        super();
    }

    /**
     * Get a byte array from a string.
     *
     * @param string String to use.
     * @return A byte array built from the string.
     */
    public static byte[] getByteArray(final String string) {
        return string.getBytes(ResourceUtil.ENCODING);
    }

    /**
     * Get a file reader.
     *
     * @param path Path of the file to read.
     * @return An input stream reader to read the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static Reader getFileReader(final File path) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path), ResourceUtil.ENCODING));
    }

    /**
     * Get a file writer.
     *
     * @param path Path of the file to write.
     * @return An output stream writer to write the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static Writer getFileWriter(final File path) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), ResourceUtil.ENCODING));
    }

    /**
     * Get a string from a byte array.
     *
     * @param array Byte array.
     * @return A string build from the byte array.
     */
    public static String getString(final byte[] array) {
        return new String(array, ResourceUtil.ENCODING);
    }

    /**
     * Get a string from a ByteArrayOutputStream.
     *
     * @param bos Byte array.
     * @return A string build from the byte array.
     */
    public static String getString(final ByteArrayOutputStream bos) {
        return ResourceUtil.getString(bos.toByteArray());
    }

    public static void createDirectoryTree(final String path) {
        if(!new File(path).mkdirs()) {
            Logger.warning("Directories were not created successfully for " + path);
        }
    }

    public static void createDirectory(final String path) {
        if(!new File(path).mkdir()) {
            Logger.warning("Directory was not created successfully for " + path);
        }
    }

    public static List<File> listFile(final File file) {
        File[] files = file.listFiles();
        if(files == null) {
            return Lists.newList();
        }
        return Arrays.asList(files);
    }
}
