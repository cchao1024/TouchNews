package com.github.cchao.touchnews.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.ui.activity.base.BaseActivity;
import com.github.cchao.touchnews.util.ImageUtil;

/**
 * Created by cchao on 2016/5/4.
 * E-mail:   cchao1024@163.com
 * Description: 图片浏览Aty
 */
public class ImageBrowseActivity extends BaseActivity {
        @Override
        protected void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( R.layout.activity_image_browse );
                String url=getIntent ().getStringExtra ( "url" );
                ImageUtil.displayImage ( this,url, ( ImageView ) findViewById ( R.id.iv_browse) );
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
                if(item.getItemId() == android.R.id.home)
                {
                        finish();
                        return true;
                }
                return super.onOptionsItemSelected(item);
        }
}
