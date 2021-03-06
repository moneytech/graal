/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.graal.compiler.amd64.test;

import static org.junit.Assume.*;

import org.junit.*;

import com.oracle.graal.amd64.*;
import com.oracle.graal.compiler.test.backend.*;

public class AMD64AllocatorTest extends AllocatorTest {

    @Before
    public void checkAMD64() {
        assumeTrue("skipping AMD64 specific test", getTarget().arch instanceof AMD64);
    }

    @Test
    public void test1() {
        testAllocation("test1snippet", 3, 1, 0);
    }

    public static long test1snippet(long x) {
        return x + 5;
    }

    @Test
    public void test2() {
        testAllocation("test2snippet", 3, 0, 0);
    }

    public static long test2snippet(long x) {
        return x * 5;
    }

    @Ignore
    @Test
    public void test3() {
        testAllocation("test3snippet", 4, 1, 0);
    }

    public static long test3snippet(long x) {
        return x / 3 + x % 3;
    }

}
