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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * Simple wrapper class for the logger to provide easy use.
 *
 * @author Grégory Van den Borre
 */
public final class Logger {

    /**
     * Wrapped logger.
     */
    private static final java.util.logging.Logger WRAPPED_LOGGER = java.util.logging.Logger.getLogger("yz");

    /**
     * Current log level.
     */
    private static LogLevel logLevel = LogLevel.DEBUG;

    /**
     * Private constructor to prevent use.
     */
    private Logger() {
        super();
    }

    public static void setLevelInfo() {
        logLevel = LogLevel.INFO;
        WRAPPED_LOGGER.setLevel(Level.INFO);
    }

    public static void setLevelDebug() {
        logLevel = LogLevel.DEBUG;
        WRAPPED_LOGGER.setLevel(Level.FINE);
    }


    /**
     * Log an error message.
     *
     * @param message Message to log.
     */
    public static void error(final String message) {
        Logger.WRAPPED_LOGGER.log(Level.SEVERE, message);
    }

    /**
     * Log an error message.
     *
     * @param message   Base error message.
     * @param exception Exception to log.
     */
    public static void error(final String message, final Throwable exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        Logger.WRAPPED_LOGGER.log(Level.SEVERE, message + " : " + sw.toString());
    }

    /**
     * Log an error message.
     *
     * @param exception Exception to log.
     */
    public static void error(final Throwable exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        Logger.WRAPPED_LOGGER.log(Level.SEVERE, sw.toString());
    }

    /**
     * Log an info message.
     *
     * @param object Message to log.
     */
    public static void info(final Object object) {
        Logger.WRAPPED_LOGGER.info(object.toString());
    }

    /**
     * Log an info message.
     *
     * @param message Message to log.
     */
    public static void info(final String message) {
        Logger.WRAPPED_LOGGER.info(message);
    }

    /**
     * Set the file to use.
     *
     * @param name File name.
     * @Requires name != null
     * @Requires name to be an acceptable file name.
     * @Affects the wrapped logger.
     * @Ensures The wrapped logger will be using the name as file name.
     */
    public static void setFile(final String name) {
        try {
            Handler file = new FileHandler(name, true);
            Logger.WRAPPED_LOGGER.addHandler(file);
        } catch (SecurityException | IOException e) {
            Logger.error(e);
        }
        Formatter formatter = new SimpleFormatter();
        for (Handler h : Logger.WRAPPED_LOGGER.getHandlers()) {
            h.setFormatter(formatter);
        }
        Logger.WRAPPED_LOGGER.setLevel(Level.ALL);
    }

    /**
     * Log a debug message.
     *
     * @param message Message to log.
     */
    public static void debug(final String message) {
        Logger.WRAPPED_LOGGER.fine(message);
    }

    /**
     * Log a warning message.
     *
     * @param message Message to log.
     */
    public static void warning(final String message) {
        Logger.WRAPPED_LOGGER.warning(message);
    }

    public static LogLevel getLogLevel() {
        return logLevel;
    }

    public static enum LogLevel {
        DEBUG, INFO, WARNING, ERROR;
    }
}
