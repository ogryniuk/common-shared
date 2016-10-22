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

package be.yildiz.common.resource;

import be.yildiz.common.exeption.ResourceCorruptedException;
import be.yildiz.common.exeption.ResourceException;
import be.yildiz.common.exeption.ResourceMissingException;
import be.yildiz.common.log.Logger;
import be.yildiz.common.util.Literals;
import lombok.Getter;
import lombok.NonNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.CRC32;

/**
 * @author Grégory Van den Borre
 */
public final class FileResource {

    /**
     * Associated File object.
     */
    private final File file;
    /**
     * Path and name of the file.
     */
    @Getter
    private String name;

    /**
     * CRC32 value.
     */
    private long crc32;

    /**
     * File size.
     */
    @Getter
    private long size;

    /**
     * Full constructor. Build the associated file, throw a
     * {@link ResourceMissingException} if the file does not exist, retrieve the
     * size, and compute the CRC32.
     *
     * @param fileName Path and name of the resource.
     */
    public FileResource(final String fileName) {
        super();
        this.file = new File(fileName);
        this.name = fileName;
        if (!this.exists()) {
            throw new ResourceMissingException("The file " + this.file.getAbsolutePath() + " does not exist.");
        }
        this.size = this.file.length();
    }

    /**
     * Full constructor. Build the associated file, throw a if the file does not
     * exist, it is created. Retrieve the size, and compute the CRC32.
     *
     * @param fileName Path and name of the resource.
     * @param type     Directory or file.
     * @throws ResourceMissingException If the file cannot be created.
     * @throws NullPointerException If fileName is null or empty or if type is null.
     */
    public FileResource(@NonNull final String fileName, @NonNull final FileType type) {
        super();
        this.file = new File(fileName);
        this.name = fileName;
        if (!this.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                if (type == FileType.DIRECTORY) {
                    this.file.mkdir();
                } else if (type == FileType.FILE && !this.file.createNewFile()) {
                    throw new ResourceMissingException(fileName + " could not be created");
                }
            } catch (IOException | SecurityException e) {
                throw new ResourceMissingException("The file " + this.file.getAbsolutePath() + " could not be created due to an exception.", e);
            }
        }
        this.size = this.file.length();
    }

    /**
     * Check if the String received(the string must match the
     * {@link FileResource#toString()}) contains the same values as this object.
     * Otherwise Exceptions will be thrown.
     *
     * @param expected Validation string.
     */
    public void check(final String expected) {
        if (this.crc32 == 0) {
            this.crc32 = this.computeCrc();
        }
        String[] values = expected.split(Literals.TOSTRING_SEPARATOR);
        long expectedCrc = Long.parseLong(values[1]);
        long expectedSize = Long.parseLong(values[2]);
        if (!this.exists()) {
            throw new ResourceMissingException("File does not exists");
        } else if (this.size != expectedSize) {
            throw new ResourceCorruptedException("Size does not match");
        } else if (this.crc32 != expectedCrc) {
            throw new ResourceCorruptedException("Crc32 does not match");
        }
    }

    /**
     * Compute the file CRC32.
     *
     * @return The computed value.
     */
    private long computeCrc() {
        CRC32 c = new CRC32();
        c.update(this.getBytesFromFile());
        return c.getValue();
    }

    /**
     * Delete the file on the hard disk and reset all attributes in this object.
     */
    public void deleteFile() {
        if (this.file.delete()) {
            this.name = "";
            this.crc32 = 0;
            this.size = 0;
        }
    }

    /**
     * Check if an other object is equals to this file, first check is made on
     * memory address, then on object class, then on crc and finally on name
     * ignoring the '\' and '/' to get equality on different systems.
     *
     * @param obj Other object to test.
     * @return <code>true</code> If the two objects are considered equals,
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileResource)) {
            return false;
        }
        FileResource other = (FileResource) obj;
        if (this.crc32 != other.crc32) {
            return false;
        }
        if (!this.name.equals(other.name)) {
            String s1 = this.unifyName(this.name);
            String s2 = this.unifyName(other.name);
            if (!s1.equals(s2)) {
                return false;
            }
        }
        return this.size == other.size;
    }

    /**
     * Check if the file is present on the hard disk.
     *
     * @return True if the file exists.
     */
    public boolean exists() {
        return this.file.exists();
    }

    /**
     * Get the file absolute path.
     *
     * @return The file absolute path.
     */
    public String getAbsolutePath() {
        return this.file.getAbsolutePath();
    }

    /**
     * Build a byte[] from the file wrapped in this object.
     *
     * @return the byte[] if not problem occurred, null otherwise.
     */
    public byte[] getBytesFromFile() {
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(this.file))) {

            if (this.size > Integer.MAX_VALUE) {
                throw new ResourceException("File too large");
            }

            byte[] bytes = new byte[(int) this.size];
            int offset = 0;
            int numRead = 0;
            while (true) {
                numRead = is.read(bytes, offset, bytes.length - offset);
                if (offset < bytes.length && numRead >= 0) {
                    offset += numRead;
                } else {
                    break;
                }
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + this.file.getName());
            }
            return bytes;
        } catch (IOException e) {
            Logger.error("File resource get bytes from file", e);
        }
        return new byte[0];
    }

    /**
     * @return The file crc32.
     */
    public long getCrc32() {
        if (this.crc32 == 0) {
            return this.computeCrc();
        }
        return this.crc32;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        int value = 0;
        if (this.name != null) {
            value = this.unifyName(this.name).hashCode();
        }
        result = prime * result + value;
        result = prime * result;
        return result;
    }

    /**
     * List all files contained in this folder.
     *
     * @param files    List to fill with result.
     * @param toIgnore If the file name contains this value, it will be ignored.
     * @throws IOException If an exception occurs during the search.
     */
    public void listFile(final List<FileResource> files, final String... toIgnore) throws IOException {
        Path folder = Paths.get(this.getName());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(folder, new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(final Path entry) {
                return !entry.toString().contains("Thumbs.db") && !entry.toString().contains("list.xml");
            }
        })) {
            for (Path p : directory) {
                if (p.toFile().isDirectory()) {
                    new FileResource(p.toString()).listFile(files);
                } else {
                    files.add(new FileResource(p.toString()));
                }
            }
        }
    }

    /**
     * Rename the file or move it if the path is changed. This only work if the
     * new name is in the same physical drive.
     *
     * @param newName New name and path of the file.
     * @return True if completed successfully.
     */
    public boolean rename(final String newName) {
        File f = new File(newName);
        if (f.getParentFile() != null && !f.getParentFile().mkdirs()) {
            return false;
        }
        return this.file.renameTo(f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name + Literals.TOSTRING_SEPARATOR + this.crc32 + Literals.TOSTRING_SEPARATOR + this.size;
    }

    /**
     * Remove OS dependent path char from the file name.
     *
     * @param toUnify File name.
     * @return The name with all '\\' and '/' char removed.
     */
    private String unifyName(final String toUnify) {
        return toUnify.contains("\\") ? toUnify.replace("\\", "") : toUnify.replace("/", "");
    }

    public enum FileType {
        FILE(0), DIRECTORY(3), VFS(2), ZIP(1);

        /**
         * Associated value to avoid to depend on the natural order.
         */
        public final int value;

        /**
         * Constructor set the value.
         *
         * @param value Associated value.
         */
        FileType(final int value) {
            this.value = value;
        }
    }
}
