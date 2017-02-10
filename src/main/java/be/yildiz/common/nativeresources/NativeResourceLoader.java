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

import be.yildiz.common.collections.Maps;
import be.yildiz.common.log.Logger;
import be.yildiz.common.resource.ZipUtil;
import be.yildiz.common.util.Util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Utility class to load the native library from the classpath or a jar.
 *
 * @author Grégory Van den Borre
 */
public final class NativeResourceLoader {

    /**
     * Directory containing the native libraries, can be win32, win34, linux32,
     * linux64 depending on the operating system and the underlying
     * architecture.
     */
    public final String directory;
    /**
     * Will contains the native librairies to be loaded.
     */
    public final File libDirectory;
    /**
     * Library file extension, can be .dll on windows, .so on linux.
     */
    public final String libraryExtension;
    /**
     * Contains the found native libraries and their full path.
     */
    private final Map<String, String> availableLib = Maps.newMap();

    public NativeResourceLoader(String path) {
        super();
        if (Util.isLinux()) {
            libraryExtension = ".so";
            if (Util.isX86()) {
                directory = "linux32";
            } else {
                directory = "linux64";
            }
        } else {
            libraryExtension = ".dll";
            if (Util.isX86()) {
                directory = "win32";
            } else {
                directory = "win64";
            }
        }
        this.libDirectory = new File(path);
        Arrays.stream(System.getProperty("java.class.path", "").split(File.pathSeparator))
                .filter(s -> s.endsWith(".jar"))
                .map(File::new)
                .forEach(app -> {
                    System.out.println(app); ZipUtil.extractFilesFromDirectory(app, this.directory, libDirectory.getAbsolutePath());});
        this.registerLibInDir();
    }

    public NativeResourceLoader() {
        this(System.getProperty("user.home") + File.separator + "app-root" + File.separator + "data");
    }

    /**
     * Give the full path of a registered native library.
     *
     * @param lib Library to check.
     * @return The absolute path of the given library.
     */
    public String getLibPath(final String lib) {
        if(lib == null) {
            throw new AssertionError("lib cannot be null.");
        }
        File f = new File(lib.endsWith(libraryExtension) ? lib : lib + libraryExtension);
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        String nativePath = this.availableLib.get(lib);
        if (nativePath == null) {
            nativePath = "/usr/lib/x86_64-linux-gnu/" + lib + ".so";
            if(!new File(nativePath).exists()) {
                throw new AssertionError(lib + " has not been found in path.");
            }
        }
        return nativePath;
    }

    /**
     * Load a native library, it will check if it is contained in a jar, if so,
     * the library will be extracted in a temporary place and loaded from there.
     *
     * @param libs Native library name to load.
     */
    public void loadLibrary(final String... libs) {
        String nativePath;
        for (String lib : libs) {
            Logger.info("Loading native : " + lib);
            nativePath = getLibPath(lib);
            System.load(nativePath);
            Logger.info(nativePath + " loaded.");
        }
    }

    /**
     * Register the found libraries in a directory to be ready to be loaded.
     *
     * @param dir Directory holding the libraries.
     */
    private void registerLibInDir(final File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] contents = dir.listFiles(p ->
                p.isFile() && p.getName().endsWith(this.libraryExtension)
            );
            for (File f : contents) {
                this.availableLib.put(f.getName().replace(this.libraryExtension, ""), f.getAbsolutePath());
            }
        }
    }

    public void registerLibInDir() {
        registerLibInDir(new File(libDirectory.getAbsolutePath() + File.separator + this.directory));
    }

    /**
     * To load the shared libraries, only used for windows, on linux, will not
     * load anything.
     *
     * @param libs Libraries to be loaded only on windows.
     */
    public void loadBaseLibrary(String... libs) {
        if (!Util.isLinux()) {
            loadLibrary(libs);
        }
    }

    public List<String> getLoadedLibraries() {
        try {
            Field lib = ClassLoader.class.getDeclaredField("loadedLibraryNames");
            lib.setAccessible(true);
            return new ArrayList<>(Vector.class.cast(lib.get(ClassLoader.getSystemClassLoader())));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
