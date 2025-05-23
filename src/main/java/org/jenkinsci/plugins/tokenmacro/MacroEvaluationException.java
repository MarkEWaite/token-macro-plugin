/*
 * The MIT License
 *
 * Copyright 2011 CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.tokenmacro;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Signals that the evaluation of the macro has failed, and the error should be presented to users
 * without a stack trace.
 *
 * @author Kohsuke Kawaguchi
 */
public class MacroEvaluationException extends Exception {

    private final @CheckForNull String macroName;

    public MacroEvaluationException(@NonNull String message) {
        this(message, null, null);
    }

    public MacroEvaluationException(@NonNull String message, @CheckForNull Throwable cause) {
        this(message, null, cause);
    }

    public MacroEvaluationException(@CheckForNull String message, @NonNull String macroName) {
        this(message, macroName, null);
    }

    public MacroEvaluationException(
            @CheckForNull String message, @CheckForNull String macroName, @CheckForNull Throwable cause) {
        super(message, cause);
        this.macroName = macroName;
    }

    @CheckForNull
    public String getMacroName() {
        return macroName;
    }

    @Override
    public String getMessage() {
        final String prefix = macroName != null ? "In " + macroName + ": " : "";
        return prefix + super.getMessage();
    }
}
