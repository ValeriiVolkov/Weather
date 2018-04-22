/*
 * Copyright (c) New Cloud Technologies, Ltd., 2013-2018
 *
 * You can not use the contents of the file in any way without New Cloud Technologies, Ltd. written permission.
 * To obtain such a permit, you should contact New Cloud Technologies, Ltd. at http://ncloudtech.com/contact.html
 *
 */

package weather.vvolkov.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public class Log {
    private static String TAG = Log.class.getSimpleName();
    private static boolean ENABLED = Build.HARDWARE != null;
    private static final boolean LOCATION_ENABLED = true;

    public static String tag(String tag) {
        return TAG + "-" + tag;
    }

    public static void vt(String tag, String format, Object... args) {
        if (ENABLED)
            android.util.Log.v(tag, String.format(format, args) + getLocation());
    }

    public static void dt(String tag, String format, Object... args) {
        if (ENABLED)
            android.util.Log.d(tag, String.format(format, args) + getLocation());
    }

    public static void it(String tag, String format, Object... args) {
        if (ENABLED)
            android.util.Log.i(tag, String.format(format, args) + getLocation());
    }

    public static void wt(String tag, String format, Object... args) {
        if (ENABLED)
            android.util.Log.w(tag, String.format(format, args) + getLocation());
    }

    public static void wt(String tag, String message, Throwable e) {
        if (ENABLED)
            android.util.Log.w(tag, message + getLocation(), e);
    }

    public static void wt(String tag, Throwable e) {
        if (ENABLED)
            android.util.Log.w(tag, e);
    }

    public static void et(String tag, Throwable e) {
        if (ENABLED)
            android.util.Log.e(tag, e.getMessage() + getLocation(), e);
    }

    public static void et(String tag, String format, Object... args) {
        if (ENABLED)
            android.util.Log.e(tag, String.format(format, args) + getLocation());
    }

    public static void v(String format, Object... args) {
        vt(TAG, format, args);
    }

    public static void d(String format, Object... args) {
        dt(TAG, format, args);
    }

    public static void i(String format, Object... args) {
        it(TAG, format, args);
    }

    public static void w(String format, Object... args) {
        wt(TAG, format, args);
    }

    public static void w(String message, Throwable e) {
        wt(TAG, message, e);
    }

    public static void w(Throwable e) {
        wt(TAG, e);
    }

    public static void e(String format, Object... args) {
        et(TAG, format, args);
    }

    public static void e(Throwable e) {
        et(TAG, e);
    }

    public static void vtrace(int traceLength, String format, Object... args) {
        vtrace(TAG, traceLength, format, args);
    }

    public static void dtrace(int traceLength, String format, Object... args) {
        dtrace(TAG, traceLength, format, args);
    }

    public static void itrace(int traceLength, String format, Object... args) {
        itrace(TAG, traceLength, format, args);
    }

    public static void wtrace(int traceLength, String format, Object... args) {
        wtrace(TAG, traceLength, format, args);
    }

    public static void etrace(int traceLength, String format, Object... args) {
        etrace(TAG, traceLength, format, args);
    }

    public static void vtrace(String tag, int traceLength, String format, Object... args) {
        if (ENABLED)
            android.util.Log.v(tag, String.format(format, args) + getTrace(traceLength));
    }

    public static void dtrace(String tag, int traceLength, String format, Object... args) {
        if (ENABLED)
            android.util.Log.d(tag, String.format(format, args) + getTrace(traceLength));
    }

    public static void itrace(String tag, int traceLength, String format, Object... args) {
        if (ENABLED)
            android.util.Log.i(tag, String.format(format, args) + getTrace(traceLength));
    }

    public static void wtrace(String tag, int traceLength, String format, Object... args) {
        if (ENABLED)
            android.util.Log.w(tag, String.format(format, args) + getTrace(traceLength));
    }

    public static void etrace(String tag, int traceLength, String format, Object... args) {
        if (ENABLED)
            android.util.Log.e(tag, String.format(format, args) + getTrace(traceLength));
    }

    @NonNull
    private static String getTrace(int length) {
        if (!LOCATION_ENABLED)
            return "";

        final String logClassName = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        int foundIndex = -1;

        for (int i = 0; i < traces.length; i++) {
            StackTraceElement trace = traces[i];

            if (trace.getClassName().startsWith(logClassName)) {
                foundIndex = i;
            } else {
                if (foundIndex > 0)
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = foundIndex + 1; i < foundIndex + length + 1; ++i) {
            if (i > traces.length)
                break;

            StackTraceElement trace = traces[i];
            sb.append(String.format("    at %s.%s:%s\n", trace.getClassName(), trace.getMethodName(),
                    trace.getLineNumber()));
        }
        sb.delete(sb.length() - 1, sb.length());
        return "\n" + sb.toString();
    }


    @SuppressLint("DefaultLocale")
    private static String getLocation() {
        if (!LOCATION_ENABLED)
            return "";
        final String logClassName = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;
        for (StackTraceElement trace : traces) {
            try {
                String className = trace.getClassName();
                if (found) {
                    if (!className.startsWith(logClassName)) {
                        Class<?> clazz = Class.forName(className);
                        String clazzName = clazz.getSimpleName();
                        if (TextUtils.isEmpty(clazzName))
                            clazzName = clazz.getName();
                        return String.format(" [%s.%s:%d]", clazzName, trace.getMethodName(), trace.getLineNumber());
                    }
                } else if (className.startsWith(logClassName)) {
                    found = true;
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return " []";
    }

}