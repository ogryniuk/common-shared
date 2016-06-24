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

package be.yildiz.common.resource.xml;

import be.yildiz.common.collections.Lists;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public final class XMLWrapTag extends XMLTag {

    private final List<XMLTag> children;

    public XMLWrapTag(final String name) {
        super(name);
        this.children = Lists.newList();
    }

    public void addChild(final XMLTag tag) {
        this.children.add(tag);
    }

    @Override
    public String generate(final StringBuilder builder) {
        builder.append("<");
        builder.append(this.getName());
        builder.append(">");
        for (XMLTag tag : this.children) {
            tag.generate(builder);
        }
        builder.append("</");
        builder.append(this.getName());
        builder.append(">");
        return builder.toString();
    }

}
