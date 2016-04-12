package com.github.armstrong.touchnews.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by cchao on 2016/4/7.
 * E-mail:   cchao1024@163.com
 * Description: ImageView 工具类
 */
public class ImageUtil{

        public static void displayImage(Context context,String url,ImageView imageView){
                Glide.with ( context ).load ( url ).into ( imageView );
        }
        public static void displayImage(Context context,File file,ImageView imageView){
                Glide.with ( context ).load ( file ).into ( imageView );
        }
}
