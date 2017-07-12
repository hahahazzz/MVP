package com.eflashloan.wct.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 12:47
 */
public final class ResourcesUtils {
    public static String getString(@NonNull Context context, @StringRes int resId, Object... args) {
        return context.getResources().getString(resId, args);
    }

    public static int getColor(@NonNull Context context, @ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static int getDimen(@NonNull Context context, @DimenRes int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public static void setBackground(@NonNull View view, @DrawableRes int res) {
        Drawable drawable = getDrawable(view.getContext(), res);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
