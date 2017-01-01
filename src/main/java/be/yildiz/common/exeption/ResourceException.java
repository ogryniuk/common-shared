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

package be.yildiz.common.exeption;

/**
 * Exception to throw with resources errors like files.
 *
 * @author Grégory Van den Borre
 */
public class ResourceException extends BaseException {

    /**
     * Current version serial ID.
     */
    private static final long serialVersionUID = 7153938153497155709L;
    /**
     * Message preceding exception description.
     */
    private static final String ERROR_TYPE = "Resources Error: ";

    /**
     * Full constructor, log the error.
     *
     * @param message Message to print to the user.
     */
    public ResourceException(final String message) {
        super(ResourceException.ERROR_TYPE + message);
    }

    /**
     * Full constructor, log the error.
     *
     * @param message   Message to print to the user.
     * @param exception Original exception stack.
     */
    public ResourceException(final String message, final Throwable exception) {
        super(ResourceException.ERROR_TYPE + message, exception);
    }
}
