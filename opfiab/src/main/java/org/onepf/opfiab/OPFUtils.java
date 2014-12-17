/*
 * Copyright 2012-2014 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfiab;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

final class OPFUtils {

    private OPFUtils() {
        throw new UnsupportedOperationException();
    }


    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static boolean uiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void checkThread(final boolean expectMainThread) {
        if (expectMainThread != uiThread()) {
            throw wrongThreadException();
        }
    }

    public static void post(@NonNull final Runnable runnable) {
        HANDLER.post(runnable);
    }

    @NonNull
    public static RuntimeException wrongThreadException() {
        return new IllegalStateException(
                uiThread() ? "Must not be called from UI thread." : "Must be called from UI thread.");
    }

    @NonNull
    public static RuntimeException notInitException() {
        return new IllegalStateException("You must call init() first.");
    }

    @NonNull
    public static RuntimeException alreadyInitException() {
        return new IllegalStateException("init() was already called.");
    }
}