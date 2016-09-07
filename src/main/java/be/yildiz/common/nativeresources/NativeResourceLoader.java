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

package be.yildiz.common.nativeresources;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.collections.Maps;
import be.yildiz.common.log.Logger;
import be.yildiz.common.resource.ZipUtil;
import be.yildiz.common.util.Util;

import java.io.File;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Utility class to load the native library from the classpath or a jar.
 *
 * @author Grégory Van den Borre
 */
// FIXME delete
public final class NativeResourceLoader {

    /**
     * Directory containing the native libraries, can be win32, win34, linux32,
     * linux64 depending on the operating system and the underlying
     * architecture.
     */
    public static final String DIRECTORY;
    /**
     * Will contains the native librairies to be loaded.
     */
    public static final File LIB_DIRECTORY = new File(System.getProperty("user.home") + File.separator + "app-root" + File.separator + "data");
    /**
     * Library file extension, can be .dll on windows, .so on linux.
     */
    public static final String LIBRARY_EXTENSION;
    /**
     * Contains the found native libraries and their full path.
     */
    private static final Map<String, String> AVAILABLE_LIB = Maps.newMap();

    static {

        if (Util.isLinux()) {
            LIBRARY_EXTENSION = ".so";
            if (Util.isX86()) {
                DIRECTORY = "linux32";
            } else {
                DIRECTORY = "linux64";
            }
        } else {
            LIBRARY_EXTENSION = ".dll";
            if (Util.isX86()) {
                DIRECTORY = "win32";
            } else {
                DIRECTORY = "win64";
            }
        }
        // register all available libs:
        String[] classPath = System.getProperty("java.class.path", "").split(File.pathSeparator);
        List<String> jarList = Lists.newList();
        for (String s : classPath) {
            if (s.endsWith(".jar")) {
                jarList.add(s);
            }
        }
        // register libs from jar and extract them.
        for (String current : jarList) {
            File app = new File(current);
            ZipUtil.extractFilesFromDirectory(app, NativeResourceLoader.DIRECTORY, LIB_DIRECTORY.getAbsolutePath());
        }
        NativeResourceLoader.registerLibInDir();
    }

    /**
     * Simple constructor, private to prevent use.
     */
    private NativeResourceLoader() {
        super();
    }

    /**
     * Give the full path of a registered native library.
     *
     * @param lib Library to check.
     * @return The absolute path of the given library.
     */
    public static String getLibPath(final String lib) {
        File f = new File(lib + NativeResourceLoader.LIBRARY_EXTENSION);
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        return NativeResourceLoader.AVAILABLE_LIB.get(lib);
    }

    /**
     * Load a native library, it will check if it is contained in a jar, if so,
     * the library will be extracted in a temporary place and loaded from there.
     *
     * @param libs Native library name to load.
     */
    public static void loadLibrary(final String... libs) {
        String nativePath = null;
        for (String lib : libs) {
            Logger.info("Loading native : " + lib);
            File f = new File(lib + NativeResourceLoader.LIBRARY_EXTENSION);
            if (f.exists()) {
                nativePath = f.getAbsolutePath();
            } else {
                nativePath = NativeResourceLoader.AVAILABLE_LIB.get(lib);
                if (nativePath == null) {
                    throw new InvalidParameterException(lib + " has not been found in path.");
                }

            }
            System.load(nativePath);
            Logger.info(nativePath + " loaded.");
        }
    }

    /**
     * Register the found libraries in a directory to be ready to be loaded.
     *
     * @param dir Directory holding the libraries.
     */
    private static void registerLibInDir(final File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] contents = dir.listFiles(p -> {
                return p.isFile() && p.getName().endsWith(NativeResourceLoader.LIBRARY_EXTENSION);
            });
            for (File f : contents) {
                NativeResourceLoader.AVAILABLE_LIB
                        .put(f.getName().replace(NativeResourceLoader.LIBRARY_EXTENSION, ""), f.getAbsolutePath());
            }
        }
    }

    public static void registerLibInDir() {
        registerLibInDir(new File(LIB_DIRECTORY.getAbsolutePath() + File.separator + NativeResourceLoader.DIRECTORY));
    }

    /**
     * To load the shared libraries, only used for windows, on linux, will not
     * load anything.
     *
     * @param libs Libraries to be loaded only on windows.
     */
    public static void loadBaseLibrary(String... libs) {
        if (!Util.isLinux()) {
            loadLibrary(libs);
        }
    }

    public static List<String> getLoadedLibraries() {
        try {
            Field lib = ClassLoader.class.getDeclaredField("loadedLibraryNames");
            lib.setAccessible(true);
            return new ArrayList<String>(Vector.class.cast(lib.get(ClassLoader.getSystemClassLoader())));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
