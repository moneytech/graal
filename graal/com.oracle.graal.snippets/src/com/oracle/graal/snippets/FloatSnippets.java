/*
 * Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.graal.snippets;

import com.oracle.graal.nodes.calc.*;
import com.oracle.graal.snippets.ClassSubstitution.*;

/**
 * Snippets for {@link java.lang.Float} methods.
 */
@ClassSubstitution(java.lang.Float.class)
public class FloatSnippets implements SnippetsInterface {

    private static final int NAN_RAW_INT_BITS = Float.floatToRawIntBits(Float.NaN);

    @MethodSubstitution
    public static int floatToRawIntBits(float value) {
        @JavacBug(id = 6995200)
        Integer result = ConvertNode.convert(ConvertNode.Op.MOV_F2I, value);
        return result;
    }

    // TODO This method is not necessary, since the JDK method does exactly this
    @MethodSubstitution
    public static int floatToIntBits(float value) {
        if (value != value) {
            return NAN_RAW_INT_BITS;
        } else {
            return floatToRawIntBits(value);
        }
    }

    @MethodSubstitution
    public static float intBitsToFloat(int bits) {
        @JavacBug(id = 6995200)
        Float result = ConvertNode.convert(ConvertNode.Op.MOV_I2F, bits);
        return result;
    }
}
