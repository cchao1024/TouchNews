package com.github.armstrong.touchnews.presenter.i;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description: 音乐presenter 接口
 */
public interface IMusicPresenter {
        void onPlay();
        void onNext();
        void onDestroy ( );
        void getMusic(String musicName);
}
