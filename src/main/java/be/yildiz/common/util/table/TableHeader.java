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

package be.yildiz.common.util.table;

import java.util.Arrays;
import java.util.List;

/**
 * A table header will contains the title for the different columns of the table.
 * @author Grégory Van den Borre
 */
public class TableHeader {

    /**
     * List of column titles.
     */
    private final List<String> columns;

    /**
     * Create a new header from a list of String.
     * @param columnTitle Each value will be the title of a column.
     */
    public TableHeader(final String... columnTitle) {
        super();
        this.columns = Arrays.asList(columnTitle);
        if(this.columns.contains(null)) {
            throw new NullPointerException("Null values are not allowed.");
        }
    }

    /**
     * @return The number of columns.
     */
    //@Ensures("size >=0")
    public int size() {
        return this.columns.size();
    }

    /**
     * @return The list of column titles.
     */
    //@Ensures("result != null")
    public List<String> getColums() {
        return this.columns;
    }
}
