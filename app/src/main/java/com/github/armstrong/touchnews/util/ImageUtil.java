package com.github.armstrong.touchnews.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 * Created by cchao on 2016/4/7.
 * E-mail:   cchao1024@163.com
 * Description: ImageView 工具类
 */
public class ImageUtil {

        public static void displayImage ( Context context, String url, ImageView imageView ) {
                Glide.with ( context ).load ( url ).into ( imageView );

        }

        public static void displayImage ( Context context, File file, ImageView imageView ) {
                Glide.with ( context ).load ( file ).into ( imageView );
        }

        public static void displayCircularImage ( final Context context, String url, final ImageView imageView ) {
                Glide.with ( context ).load ( url ).asBitmap ( ).centerCrop ( ).into ( new BitmapImageViewTarget ( imageView ) {
                        @Override
                        protected void setResource ( Bitmap resource ) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create ( context.getResources (), resource );
                                circularBitmapDrawable.setCircular ( true );
                                imageView.setImageDrawable ( circularBitmapDrawable );
                        }
                } );
        }
}
