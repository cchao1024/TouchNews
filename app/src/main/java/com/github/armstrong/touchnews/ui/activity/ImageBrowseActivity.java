package com.github.armstrong.touchnews.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.ui.activity.base.BaseActivity;
import com.github.armstrong.touchnews.util.ImageUtil;

/**
 * Created by cchao on 2016/5/4.
 * E-mail:   cchao1024@163.com
 * Description: 图片浏览Aty
 */
public class ImageBrowseActivity extends BaseActivity{
        @Override
        protected void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( R.layout.activity_image_browse );
                String url=getIntent ().getStringExtra ( "url" );
                ImageUtil.displayImage ( this,url, ( ImageView ) findViewById ( R .id.iv_browse) );
        }
}
