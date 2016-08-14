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

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A table is a two dimensional data container used to display and sort values.
 * @param <T> Type of the contained element.
 * @author Grégory Van den Borre
 */
public class Table <T> {

    /**
     * Table header.
     */
    private final TableHeader header;

    /**
     * List of all rows contained in the table.
     */
    private final List<TableRow> rows = new ArrayList<>();

    /**
     * Build a table from
     * @param header Table header.
     * @param objects Table content.
     */
    public Table(@NonNull final TableHeader header, @NonNull final List<TableRow> objects) {
        super();
        this.header = header;
        this.rows.addAll(objects);

    }

    public void reorder(final int column) {
        Collections.sort(rows, Comparator.comparing(o -> o.getColumn(column)));
    }

    public void reorderInvert(final int column) {
        this.reorder(column);
        Collections.reverse(this.rows);
    }

    public int getWidth() {
        return header.size();
    }

    public int getHeight() {
        return rows.size();
    }

    public void addRow(TableRow r) {
        this.rows.add(r);
    }

    public Comparable getValue(final int column, final int row) {
        return this.rows.get(row).getColumn(column);
    }

    public void removeRow(T rowValue) {
        for(int i = 0; i < this.rows.size(); i++) {
            if(rows.get(i).getObject().equals(rowValue)) {
                this.rows.remove(i);
                break;
            }
        }
    }
}
