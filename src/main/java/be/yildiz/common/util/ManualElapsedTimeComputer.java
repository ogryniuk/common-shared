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

package be.yildiz.common.util;

import lombok.Getter;

public class ManualElapsedTimeComputer {

    /**
     * Total time to wait.
     */
    private final long timeToWait;
    /**
     * Current elapsed time.
     */
    @Getter
    private long elapsedTime;

    /**
     * Constructor initialize the last action time with the current time.
     *
     * @param deltaTime Counter to wait between the last check and the current time,
     *                  in milliseconds.
     */
    public ManualElapsedTimeComputer(final long deltaTime) {
        super();
        this.timeToWait = deltaTime;
    }


    public boolean isTimeElapsed(long timeToAdd) {
        this.elapsedTime += timeToAdd;
        if (this.elapsedTime > this.timeToWait) {
            this.elapsedTime = 0;
            return true;
        }
        return false;
    }
}
