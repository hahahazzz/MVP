package com.eflashloan.wct.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.eflashloan.wct.R;

import java.security.MessageDigest;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 9:27
 */
public final class ImageLoader {
    private static volatile ImageLoader loader;
    private final RequestOptions requestOptions;

    private ImageLoader() {
        requestOptions = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
    }

    public static ImageLoader getLoader() {
        if (loader == null) {
            synchronized (ImageLoader.class) {
                if (loader == null) {
                    loader = new ImageLoader();
                }
            }
        }
        return loader;
    }

    public void loadAvatar(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(requestOptions.transform(CenterInsideTransformation.get())).into(imageView);
    }

    public void loadFitCenter(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(requestOptions.fitCenter()).into(imageView);
    }

    public void loadCenterCrop(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(requestOptions.centerCrop()).into(imageView);
    }

    public void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context).asGif().load(url).into(imageView);
    }

    private static final class CenterInsideTransformation extends BitmapTransformation {
        private static final String ID = "com.bumptech.glide.load.resource.bitmap.CenterInsideTransformation";

        public static CenterInsideTransformation get() {
            return new CenterInsideTransformation();
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            final Bitmap toReuse = pool.get(outWidth, outHeight, toTransform.getConfig() != null
                    ? toTransform.getConfig() : Bitmap.Config.ARGB_8888);
            Bitmap transformed = centerInside(toReuse, toTransform, outWidth, outHeight);
            return transformed;
        }

        public static Bitmap centerInside(Bitmap recycle, Bitmap toCenter, int width, int height) {
            if (toCenter == null) {
                return null;
            }
            if (toCenter.getWidth() == width && toCenter.getHeight() == height) {
                return toCenter;
            }
            Matrix matrix = new Matrix();
            matrix.setScale(width * 1.0f / toCenter.getWidth(), height * 1.0f / toCenter.getHeight());
            final Bitmap result;
            if (recycle != null) {
                result = recycle;
            } else {
                result = Bitmap.createBitmap(width, height, toCenter.getConfig() != null ? toCenter
                        .getConfig() : Bitmap.Config.ARGB_8888);
            }
            result.setHasAlpha(toCenter.hasAlpha());
            Paint paint = new Paint(Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(toCenter, matrix, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {
            messageDigest.update(ID.getBytes(Key.CHARSET));
        }
    }
}
