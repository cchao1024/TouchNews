package com.github.cchao.touchnews.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.javaBean.MusicEntity;
import com.github.cchao.touchnews.presenter.MusicPresenter;
import com.github.cchao.touchnews.presenter.i.IMusicPresenter;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;
import com.github.cchao.touchnews.util.ImageUtil;
import com.github.cchao.touchnews.view.IMusicView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description: 音乐播放界面
 */

public class MusicFragment extends BaseFragment implements IMusicView {
        @Bind ( R.id.root_music_fragment )
        RelativeLayout mRootLayout;
        @Bind ( R.id.btn_music_play )
        ImageButton mPlayBtn;
        @Bind ( R.id.list_view_music_search )
        ListView mListView;
        @Bind ( R.id.btn_music_next )
        ImageButton mNextBtn;
        @Bind ( R.id.tv_music__name )
        TextView mMusicName;
        @Bind ( R.id.tv_music_singer )
        TextView mMusicSinger;
        @Bind ( R.id.iv_music_album )
        ImageView mAlbum;
        View mViewRoot;
        SearchView mMenuItemSearch;
        private IMusicPresenter mMusicsPresenter = null;
        MusicEntity mCurMusic;
        List< String> mSearchChoose;
        List< String> mSearchChooseDef;
        ArrayAdapter< String > mStringArrayAdapter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setHasOptionsMenu ( true );
        }

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                mViewRoot = inflater.inflate ( R.layout.fragment_music, null );
                return mViewRoot;
        }
        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mToolbar.setTitle ( R.string.music );
                mMusicsPresenter = new MusicPresenter ( mContext, this );
                mSearchChooseDef = new ArrayList ( );
                mSearchChoose = new ArrayList ( );
                //默认第一次搜索为类别 R.array.music_search_default

                for ( String str : ( getResources ( ).getStringArray ( R.array.music_search_default ) ) ) {
                        mSearchChooseDef.add ( str );
                        mSearchChoose.add ( str );
                }
                //进入页面就播放随机一种类别歌曲
                mMusicsPresenter.getMusic ( mSearchChoose.get ( ( int ) ( Math.random ( ) * mSearchChoose.size ( ) ) ) );
                initSearchList ( );
        }


        private void initSearchList ( ) {
                mListView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
                        @Override
                        public void onItemClick ( AdapterView< ? > parent, View view, int position, long id ) {
                                //待选关键字 点击  List 关闭
                                mListView.setVisibility ( View.GONE );
                                //根据歌曲名搜索歌曲播放地址
                                mMusicsPresenter.getMusic ( ( ( TextView ) view ).getText ( ).toString ( ) );
                                mMenuItemSearch.clearFocus ( );
                                mMenuItemSearch.onActionViewCollapsed ( );
                        }
                } );
                mStringArrayAdapter = new ArrayAdapter< String > ( mContext, R.layout.item_music_search_item, mSearchChoose );
                mListView.setAdapter ( mStringArrayAdapter );
        }

        /**
         * @param result 根据输入关键字返回的待选
         */
        @Override
        public void updateSearchList ( List< String > result ) {
                if ( result != null && result.size ( ) > 1 ) {
                        mSearchChoose.clear ( );
                        mSearchChoose.addAll ( result );
                        mStringArrayAdapter.notifyDataSetChanged ( );
                }
        }

        @OnClick ( { R.id.btn_music_next , R.id.btn_music_play } )
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
                        case R.id.btn_music_play:
                                mMusicsPresenter.onPlay ( );
                                break;
                        case R.id.btn_music_next:
                                mMusicsPresenter.onNext ( );
                                break;
                }
        }

        @Override
        public void onCreateOptionsMenu ( Menu menu, MenuInflater inflater ) {
                inflater.inflate ( R.menu.menu_music, menu );
                super.onCreateOptionsMenu ( menu, inflater );
                mMenuItemSearch = ( SearchView ) MenuItemCompat.getActionView ( menu.findItem ( R.id.action_search ) );
                mMenuItemSearch.setOnCloseListener ( new SearchView.OnCloseListener ( ) {
                        @Override
                        public boolean onClose ( ) {
                                mListView.setVisibility ( View.GONE );
                                //刷新待选List 为默认值
                                updateSearchList ( mSearchChooseDef );
                                return false;
                        }
                } );
                //文本输入框可见、显示ListView
                mMenuItemSearch.setOnQueryTextFocusChangeListener ( new View.OnFocusChangeListener ( ) {
                        @Override
                        public void onFocusChange ( View v, boolean hasFocus ) {
                                if ( hasFocus ) {
                                        mListView.setVisibility ( View.VISIBLE );
                                }
                        }
                } );
                mMenuItemSearch.setOnQueryTextListener ( new SearchView.OnQueryTextListener ( ) {
                        @Override
                        public boolean onQueryTextSubmit ( String query ) {
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange ( String newText ) {
                                //文本输入改变实时搜索关键字
                                mMusicsPresenter.searchMusic ( newText );
                                //文本输入改变 List 滑动到顶部
                                mListView.setSelection ( 0 );
                                return true;
                        }
                } );

        }

        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                switch ( item.getItemId ( ) ) {
                        case R.id.action_search:
                                //清空搜索结果-List
                                mSearchChoose.clear ( );
                                mStringArrayAdapter.notifyDataSetChanged ( );
                                return true;
                }
                return false;
        }

        @Override
        public void onDestroy ( ) {
                super.onDestroy ( );
                mMusicsPresenter.onDestroy ();
        }

        /**
         * 音乐开始播放
         */
        @Override
        public void onMusicPlay ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_pause );
                Snackbar.make ( mViewRoot, "onMusicPlay", Snackbar.LENGTH_SHORT ).show ( );
        }
        /**
         * 音乐暂停
         */
        @Override
        public void onMusicPause ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_play );
                Snackbar.make ( mViewRoot, "onMusicPause", Snackbar.LENGTH_SHORT ).show ( );
        }
        /**
         * 暂停后播放
         */
        @Override
        public void onResumePlay ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_pause );
        }

        @Override
        public void onMusicPrepare ( MusicEntity curMusic ) {
                if ( curMusic != null ) {
                        mCurMusic = curMusic;
                        mMusicName.setText ( mCurMusic.getMusicInfo ( ).getSongname ( ) );
                        mMusicSinger.setText ( mCurMusic.getMusicInfo ( ).getSingername ( ) );
                        mPlayBtn.setImageResource ( R.drawable.btn_music_play );
                        Snackbar.make ( mViewRoot, "PREPARE", Snackbar.LENGTH_SHORT ).show ( );
                }
        }

        /**
         *  设置专辑图 、 背景高斯
         */
        @Override
        public void setAlbum ( ) {
                if ( mCurMusic != null && mCurMusic.getMusicSinger ( ) != null ) {
                        String url = mCurMusic.getMusicSinger ( ).getImage ( );
                        ImageUtil.displayCircularImage ( mContext, url, mAlbum );
//                        ImageUtil.displayBlurImage ( mContext,url,mRootLayout );
                }
        }


}

