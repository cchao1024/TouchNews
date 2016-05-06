package com.github.armstrong.touchnews.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

//import jp.wasabeef.glide.transformations.BlurTransformation;

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
                                        RoundedBitmapDrawableFactory.create ( context.getResources ( ), resource );
                                circularBitmapDrawable.setCircular ( true );
                                imageView.setImageDrawable ( circularBitmapDrawable );

                        }
                } );
        }

        /*public static void displayBlurImage ( Context context, String url, ImageView imageView ) {
                Glide.with ( context ).load ( url )
                        .bitmapTransform ( new BlurTransformation ( context ) )
                        .into ( imageView );
        }
        public static void displayBlurImage ( Context context, String url, final View view ) {
                Glide.with ( context ).load ( url )
                        .bitmapTransform ( new BlurTransformation ( context ) ).into ( new SimpleTarget< GlideDrawable > ( ) {
                        @Override
                        public void onResourceReady ( GlideDrawable resource, GlideAnimation< ? super GlideDrawable > glideAnimation ) {
                                view.setBackground ( resource );
                        }
                } );
        }*/

}
