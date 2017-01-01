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

package be.yildiz.common.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Simple formatter for a logger.
 *
 * @author Grégory Van den Borre
 */
public final class SimpleFormatter extends Formatter {

    /**
     * Date to print in the log.
     */
    private final Date date = new Date();

    /**
     * Date format.
     */
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Format the log with the following pattern: date : message.
     *
     * @param record Contains log message data.
     * @return The formatted log entry.
     */
    @Override
    public String format(final LogRecord record) {
        StringBuilder builder = new StringBuilder();
        this.date.setTime(record.getMillis());
        String level = record.getLevel().toString().toUpperCase();
        if ("FINE".equals(level)) {
            level = "DEBUG";
        } else if ("INFO".equals(level)) {
            level = "INFOS";
        }
        builder.append(level);
        builder.append(": ");
        builder.append(this.format.format(this.date));
        builder.append(": ");
        builder.append(record.getMessage());
        builder.append('\n');
        return builder.toString();
    }
}
