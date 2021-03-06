/*
 * Copyright (c) 2012, 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
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
package com.oracle.truffle.api.dsl;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Specialization {

    /**
     * @deprecated do not use anymore. Will get removed in the next release.
     */
    @Deprecated int DEFAULT_ORDER = -1;

    /**
     * The order has no effect anymore. The declaration order specialization methods is used
     * instead.
     *
     * @deprecated use declaration order instead. Will get removed in the next release.
     */
    @Deprecated
    int order() default DEFAULT_ORDER;

    /**
     * Inserts this and all specializations that are declared after this specialization before a
     * specialization in the superclass. By default all specializations of the subclass are appended
     * to the specializations of the superclass.
     */
    String insertBefore() default "";

    Class<? extends Throwable>[] rewriteOn() default {};

    /**
     * The contains attribute declares all specializations that are contained by this
     * specialization. A containing specialization must be strictly generic as the contained
     * specialization.
     */
    String[] contains() default {};

    String[] guards() default {};

    /**
     * Defines the assumptions to check for this specialization. When the specialization method is
     * invoked it is guaranteed that these assumptions still hold. It is not guaranteed that they
     * are checked before the {@link #guards()} methods. They may be checked before after or in
     * between {@link #guards()}. To declare assumptions use the {@link NodeAssumptions} annotation
     * at class level.
     */
    String[] assumptions() default {};

}
