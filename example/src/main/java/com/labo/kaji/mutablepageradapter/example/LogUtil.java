package com.labo.kaji.mutablepageradapter.example;

import android.util.Log;

public class LogUtil {

    public static final String DEFAULT_TAG = "LogUtil";
    private static final boolean DEBUG = true;

    public static void V(String TAG, String ClassName, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.v(TAG, String.format(format, args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void D(String TAG, String ClassName, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.d(TAG, String.format(format, args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void I(String TAG, String ClassName, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.i(TAG, String.format(format, args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void W(String TAG, String ClassName, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.w(TAG, String.format(format, args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void E(String TAG, String ClassName, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.e(TAG, String.format(format, args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void E(String TAG, String ClassName, Throwable t, String format, Object... args) {
        if (DEBUG) {
            format = new StringBuilder("[").append(ClassName).append("] ").append(format).toString();
            try {
                Log.e(TAG, String.format(format, args), t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void E(String TAG, String ClassName, Throwable t) {
        if (DEBUG) {
            try {
                Log.e(TAG, "", t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void V(String TAG, Class<?> c, String format, Object... args) {
        LogUtil.V(TAG, c.getSimpleName(), format, args);
    }

    public static void D(String TAG, Class<?> c, String format, Object... args) {
        LogUtil.D(TAG, c.getSimpleName(), format, args);
    }

    public static void I(String TAG, Class<?> c, String format, Object... args) {
        LogUtil.I(TAG, c.getSimpleName(), format, args);
    }

    public static void W(String TAG, Class<?> c, String format, Object... args) {
        LogUtil.W(TAG, c.getSimpleName(), format, args);
    }

    public static void E(String TAG, Class<?> c, String format, Object... args) {
        LogUtil.E(TAG, c.getSimpleName(), format, args);
    }

    public static void E(String TAG, Class<?> c, Throwable t, String format, Object... args) {
        LogUtil.E(TAG, c.getSimpleName(), t, format, args);
    }

    public static void E(String TAG, Class<?> c, Throwable t) {
        LogUtil.E(TAG, c.getSimpleName(), t);
    }


    public static void V(Class<?> c, String format, Object... args) {
        LogUtil.V(DEFAULT_TAG, c.getSimpleName(), format, args);
    }

    public static void D(Class<?> c, String format, Object... args) {
        LogUtil.D(DEFAULT_TAG, c.getSimpleName(), format, args);
    }

    public static void I(Class<?> c, String format, Object... args) {
        LogUtil.I(DEFAULT_TAG, c.getSimpleName(), format, args);
    }

    public static void W(Class<?> c, String format, Object... args) {
        LogUtil.W(DEFAULT_TAG, c.getSimpleName(), format, args);
    }

    public static void E(Class<?> c, String format, Object... args) {
        LogUtil.E(DEFAULT_TAG, c.getSimpleName(), format, args);
    }

    public static void E(Class<?> c, Throwable t, String format, Object... args) {
        LogUtil.E(DEFAULT_TAG, c.getSimpleName(), t, format, args);
    }

    public static void E(Class<?> c, Throwable t) {
        LogUtil.E(DEFAULT_TAG, c.getSimpleName(), t);
    }

}
