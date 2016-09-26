package com.github.cchao.touchnews.contract;

import com.github.cchao.touchnews.javaBean.MusicEntity;

import java.util.List;

/**
 * Created by cchao on 2016/9/26.
 * E-mail:   cchao1024@163.com
 * Description:
 */

public interface MusicContract {
        interface View {
                void onMusicPlay ( );

                void onMusicPause ( );

                void onResumePlay ( );

                void onMusicPrepare ( MusicEntity curMusic );

                void setAlbum ( );

                void updateSearchList ( List< String > result );
        }

        interface Presenter {

                void onPlay ( );

                void onNext ( );

                void onDestroy ( );

                void getMusic ( String musicName );

                void searchMusic ( String key );
        }

}