/*
 * Copyright (c) 2009, 2011, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.max.graal.compiler.ir;

import java.util.*;

import com.oracle.max.graal.compiler.nodes.spi.*;
import com.oracle.max.graal.graph.*;
import com.sun.cri.ci.*;

@NodeInfo(shortName = "Deopt")
public class Deoptimize extends FixedNode {

    public static enum DeoptAction {
        None,                           // just interpret, do not invalidate nmethod
        Recompile,                      // recompile the nmethod; need not invalidate
        InvalidateReprofile,            // invalidate the nmethod, reset IC, maybe recompile
        InvalidateRecompile,            // invalidate the nmethod, recompile (probably)
        InvalidateStopCompiling,        // invalidate the nmethod and do not compile
    }

    private String message;
    private final DeoptAction action;

    public Deoptimize(DeoptAction action, Graph graph) {
        super(CiKind.Illegal, graph);
        this.action = action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public DeoptAction action() {
        return action;
    }

    @Override
    public void accept(ValueVisitor v) {
        v.visitDeoptimize(this);
    }

    @Override
    public Map<Object, Object> getDebugProperties() {
        Map<Object, Object> properties = super.getDebugProperties();
        properties.put("message", message);
        properties.put("action", action);
        return properties;
    }
}
